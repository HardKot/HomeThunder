package com.homethunder.homethunder.useCase.user;

import com.homethunder.homethunder.domain.user.Gender;
import com.homethunder.homethunder.domain.user.IUserGateway;
import com.homethunder.homethunder.domain.user.User;
import com.homethunder.homethunder.useCase.user.dto.IUserNewPassword;
import com.homethunder.homethunder.useCase.user.dto.IUserRegistration;
import com.homethunder.homethunder.useCase.user.dto.IUserUpdate;
import com.leakyabstractions.result.api.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.*;
import java.util.Optional;

import static org.mockito.Mockito.mockStatic;


class UserInteractTest {

    private IUserGateway userGateway;

    private final UserInteract userInteract = new UserInteract();

    private User testUser;

    @BeforeEach
    void generateGateway() {
        userGateway = Mockito.mock(IUserGateway.class);

        Mockito.when(userGateway.passwordEncoder("password")).thenReturn("password");
        Mockito.when(userGateway.create(Mockito.any(User.class))).thenReturn(null);
        Mockito.when(userGateway.generateTokenForEmail(Mockito.any(), Mockito.anyString())).thenReturn("token");

        Mockito.when(userGateway.update(Mockito.any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        userInteract.setUserGateway(userGateway);
    }

    @BeforeEach
    void generateUser() {
        testUser = new User();
        testUser.setFirstname("Иван");
        testUser.setLastname("Иванов");
        testUser.setPatronymic("Иванович");
        testUser.setPassword("password");
        testUser.setEmail("email@email.ru");
        testUser.setGender(Gender.Male);
        testUser.setBirthday(LocalDate.of(2000, Month.JANUARY, 1));
    }

    // Registration
    IUserRegistration userRegistrationDTO() {
        IUserRegistration userRegistrationDTO = Mockito.mock(IUserRegistration.class);
        Mockito.when(userRegistrationDTO.firstname()).thenReturn("Иван");
        Mockito.when(userRegistrationDTO.lastname()).thenReturn("Иванов");
        Mockito.when(userRegistrationDTO.patronymic()).thenReturn("Иванович");
        Mockito.when(userRegistrationDTO.avatarURI()).thenReturn(null);
        Mockito.when(userRegistrationDTO.gender()).thenReturn(Gender.Male);
        Mockito.when(userRegistrationDTO.birthday()).thenReturn(LocalDate.of(2000, Month.JANUARY, 1));
        Mockito.when(userRegistrationDTO.email()).thenReturn("email@email.ru");
        Mockito.when(userRegistrationDTO.password()).thenReturn("password");
        Mockito.when(userRegistrationDTO.confirmPassword()).thenReturn("password");
        Mockito.when(userRegistrationDTO.ip()).thenReturn("127.0.0.1");
        Mockito.when(userRegistrationDTO.deviceName()).thenReturn( "Test");
        return userRegistrationDTO;
    }

    @Tag("Registration")
    @Test
    void registrationSuccessTest() {
        Mockito.when(userGateway.findByEmail("email@email.ru")).thenReturn(Optional.empty());
        IUserRegistration dto = userRegistrationDTO();

        Result<User, UserInteractError> result = userInteract.registration(dto);

        Assertions.assertTrue(result.hasSuccess());
        Assertions.assertTrue(result.getSuccess().isPresent());
        Assertions.assertEquals(testUser, result.getSuccess().get());
    }

    @Tag("Registration")
    @Test
    void registrationEmailUsingTest() {
        Mockito.when(userGateway.findByEmail("email@email.ru")).thenReturn(Optional.of(new User()));
        IUserRegistration dto = userRegistrationDTO();
        Result<User, UserInteractError> result = userInteract.registration(dto);

        Assertions.assertTrue(result.hasFailure());
        Assertions.assertTrue(result.getFailure().isPresent());
        Assertions.assertInstanceOf(UserInteractError.EmailExists.class, result.getFailure().get());
    }

    @Tag("Registration")
    @Test
    void registrationPasswordNotConfirmTest() {
        Mockito.when(userGateway.findByEmail("email@email.ru")).thenReturn(Optional.empty());
        IUserRegistration dto = userRegistrationDTO();
        Mockito.when(dto.confirmPassword()).thenReturn("noMatchPassword");

        Result<User, UserInteractError> result = userInteract.registration(dto);

        Assertions.assertTrue(result.hasFailure());
        Assertions.assertTrue(result.getFailure().isPresent());
        Assertions.assertInstanceOf(UserInteractError.PasswordNotConfirm.class, result.getFailure().get());
    }

    // Update
    IUserUpdate userUpdateDTO() {
        IUserUpdate userUpdateDTO = Mockito.mock(IUserUpdate.class);
        Mockito.when(userUpdateDTO.firstname()).thenReturn("Петр");
        Mockito.when(userUpdateDTO.lastname()).thenReturn("Петров");
        Mockito.when(userUpdateDTO.patronymic()).thenReturn("Петрович");
        Mockito.when(userUpdateDTO.birthday()).thenReturn(LocalDate.of(2001, Month.JANUARY, 1));
        Mockito.when(userUpdateDTO.gender()).thenReturn(Gender.Unknown);
        Mockito.when(userUpdateDTO.avatarURI()).thenReturn("/newAvatar.png");
        return userUpdateDTO;
    }

    @Tag("Update")
    @Test
    void updateUserTest() {
        IUserUpdate dto = userUpdateDTO();

        Result<User, UserInteractError> result = userInteract.update(testUser, dto);

        Assertions.assertTrue(result.hasSuccess());
        Assertions.assertTrue(result.getSuccess().isPresent());

        Assertions.assertEquals("Петр", result.getSuccess().get().getFirstname());
        Assertions.assertEquals("Петров", result.getSuccess().get().getLastname());
        Assertions.assertEquals("Петрович", result.getSuccess().get().getPatronymic());
        Assertions.assertEquals(LocalDate.of(2001, Month.JANUARY, 1), result.getSuccess().get().getBirthday());
        Assertions.assertEquals(Gender.Unknown, result.getSuccess().get().getGender());
    }

    @Tag("Update")
    @Test
    void updateUserIfDeletedTest() {
        IUserUpdate dto = userUpdateDTO();

        testUser.setDeletedAt(LocalDateTime.of(2024, Month.MAY, 12, 0, 0));

        Result<User, UserInteractError> result = userInteract.update(testUser, dto);

        Assertions.assertTrue(result.hasSuccess());
        Assertions.assertTrue(result.getSuccess().isPresent());

        Assertions.assertNull(result.getSuccess().get().getDeletedAt());
    }

    //ChangePassword

    IUserNewPassword userNewPasswordDTO() {
        IUserNewPassword dto = Mockito.mock(IUserNewPassword.class);
        Mockito.when(dto.password()).thenReturn("newPassword");
        Mockito.when(dto.confirmPassword()).thenReturn("newPassword");
        return dto;
    }

    @Tag("ChangePassword")
    @Test
    void changePasswordUserTest() {
        IUserNewPassword dto = userNewPasswordDTO();
        Mockito.when(userGateway.passwordEncoder("newPassword")).thenReturn("newPassword");

        Result<User, UserInteractError> result = userInteract.changePassword(testUser, dto);

        Assertions.assertTrue(result.hasSuccess());
        Assertions.assertTrue(result.getSuccess().isPresent());

        Assertions.assertEquals("newPassword", result.getSuccess().get().getPassword());
    }

    @Tag("ChangePassword")
    @Test
    void changePasswordPasswordNotConfirmUserTest() {
        IUserNewPassword dto = userNewPasswordDTO();
        Mockito.when(dto.confirmPassword()).thenReturn("newPassword1");
        Mockito.when(userGateway.passwordEncoder("newPassword")).thenReturn("newPassword");

        Result<User, UserInteractError> result = userInteract.changePassword(testUser, dto);

        Assertions.assertTrue(result.hasFailure());
        Assertions.assertTrue(result.getFailure().isPresent());

        Assertions.assertInstanceOf(UserInteractError.PasswordNotConfirm.class, result.getFailure().get());
    }

    //SelfDelete

    @Tag("SelfDelete")
    @Test
    void selfDeleteTest() {
        Clock clock = Clock.fixed(Instant.parse("2024-04-12T00:00:00Z"), ZoneId.of("UTC"));
        LocalDateTime localDateTime = LocalDateTime.now(clock);

        LocalDateTime deleteAt = LocalDateTime.of(2024, Month.MAY, 12, 0, 0);

        try (MockedStatic<LocalDateTime> mockedStatic = mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(localDateTime);

            Result<User, UserInteractError> result = userInteract.selfDelete(testUser);

            Assertions.assertTrue(result.hasSuccess());
            Assertions.assertTrue(result.getSuccess().isPresent());
            Assertions.assertEquals(deleteAt, result.getSuccess().get().getDeletedAt());
        }
    }

    @Tag("SelfDelete")
    @Test
    void selfDeleteIfDeletedTest() {
        Clock clock = Clock.fixed(Instant.parse("2024-04-12T00:00:00Z"), ZoneId.of("UTC"));
        LocalDateTime localDateTime = LocalDateTime.now(clock);

        LocalDateTime deleteAt = LocalDateTime.of(2024, Month.MAY, 12, 0, 0);

        testUser.setDeletedAt(LocalDateTime.of(2024, Month.MAY, 10, 0, 0));

        try (MockedStatic<LocalDateTime> mockedStatic = mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(localDateTime);

            Result<User, UserInteractError> result = userInteract.selfDelete(testUser);

            Assertions.assertTrue(result.hasSuccess());
            Assertions.assertTrue(result.getSuccess().isPresent());
            Assertions.assertEquals(deleteAt, result.getSuccess().get().getDeletedAt());
        }
    }

    @Tag("Recovery")
    @Test
    void recoveryTest() {
        testUser.setDeletedAt(LocalDateTime.of(2024, Month.MAY, 12, 0, 0));

        Result<User, UserInteractError> result = userInteract.recovery(testUser);

        Assertions.assertTrue(result.hasSuccess());
        Assertions.assertTrue(result.getSuccess().isPresent());
        Assertions.assertNull(result.getSuccess().get().getDeletedAt());
    }

    @Tag("RequestChangEmail")
    @Test
    void requestChangeEmailTest() {
        Mockito.when(userGateway.findByEmail("newEmail@email.ru")).thenReturn(Optional.empty());

        Result<User, UserInteractError> result = userInteract.requestChangeEmail(testUser, "newEmail@email.ru");

        Assertions.assertTrue(result.hasSuccess());
        Assertions.assertTrue(result.getSuccess().isPresent());
        Assertions.assertEquals("newEmail@email.ru", result.getSuccess().get().getEmail());
    }

    @Tag("RequestChangEmail")
    @Test
    void requestChangeEmailUsedTest() {
        Mockito.when(userGateway.findByEmail("newEmail@email.ru")).thenReturn(Optional.of(new User()));

        Result<User, UserInteractError> result = userInteract.requestChangeEmail(testUser, "newEmail@email.ru");

        Assertions.assertTrue(result.hasFailure());
        Assertions.assertTrue(result.getFailure().isPresent());
        Assertions.assertInstanceOf(UserInteractError.EmailExists.class, result.getFailure().get());
    }

    @Tag("RequestChangEmail")
    @Test
    void requestChangeEmailUserRequestSelfEmailTest() {
        Mockito.when(userGateway.findByEmail("email@email.ru")).thenReturn(Optional.of(testUser));

        Result<User, UserInteractError> result = userInteract.requestChangeEmail(testUser, "email@email.ru");

        Assertions.assertTrue(result.hasSuccess());
        Assertions.assertTrue(result.getSuccess().isPresent());
        Assertions.assertEquals("email@email.ru", result.getSuccess().get().getEmail());
    }

    @Tag("RequestDropPassword")
    @Test
    void requestDropEmailTest() {
        Mockito.when(userGateway.findByEmail("email@email.ru")).thenReturn(Optional.of(testUser));

        userInteract.requestDropPassword("email@email.ru");

        Mockito.verify(userGateway, Mockito.times(1)).sendEmail(Mockito.any(), Mockito.anyString());
    }

    @Tag("RequestDropPassword")
    @Test
    void requestDropPasswordEmailNoExistsTest() {
        Mockito.when(userGateway.findByEmail("email@email.ru")).thenReturn(Optional.empty());

        userInteract.requestDropPassword("email@email.ru");

        Mockito.verify(userGateway, Mockito.times(0)).sendEmail(Mockito.any(), Mockito.anyString());
    }
}

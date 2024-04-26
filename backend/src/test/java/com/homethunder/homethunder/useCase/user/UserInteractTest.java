package com.homethunder.homethunder.useCase.user;

import com.homethunder.homethunder.domain.user.Gender;
import com.homethunder.homethunder.domain.user.IUserGateway;
import com.homethunder.homethunder.domain.user.User;
import com.homethunder.homethunder.useCase.user.dto.IUserRegistration;
import com.leakyabstractions.result.api.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;



class UserInteractTest {

    private IUserGateway userGateway;
    private IUserRegistration userRegistrationDTO = new UserRegistrationDTO(
            "Иван",
            "Иванов",
            "Иванович",
            null,
            Gender.Male,
            LocalDate.of(2000, Month.JANUARY, 1),
            "email@email.ru",
            "password",
            "password",
            "127.0.0.1",
            "Test"
    );

    private UserInteract userInteract = new UserInteract();
    private User userExecute;



    @BeforeEach
    void generateGateway() {
        userGateway = Mockito.mock(IUserGateway.class);

        Mockito.when(userGateway.passwordEncoder("password")).thenReturn("password");
        Mockito.when(userGateway.create(Mockito.any(User.class))).thenReturn(null);
        Mockito.when(userGateway.generateTokenForEmail(Mockito.any(), Mockito.anyString())).thenReturn("token");
    }

    @BeforeEach
    void generateDTO() {
        userRegistrationDTO = Mockito.mock(UserRegistrationDTO.class);
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
    }

    @BeforeEach
    void generateExecute() {
        userExecute = new User();
        userExecute.setFirstname("Иван");
        userExecute.setLastname("Иванов");
        userExecute.setPatronymic("Иванович");
        userExecute.setPassword("password");
        userExecute.setEmail("email@email.ru");
        userExecute.setGender(Gender.Male);
        userExecute.setBirthday(LocalDate.of(2000, Month.JANUARY, 1));
    }

    // Registration
    @Test
    void registrationSuccessTest() {
        Mockito.when(userGateway.findByEmail("email@email.ru")).thenReturn(Optional.empty());

        userInteract.setUserGateway(userGateway);
        Result<User, UserInteractError> result = userInteract.registration(userRegistrationDTO);

        Assertions.assertTrue(result.hasSuccess());
        Assertions.assertEquals(userExecute, result.getSuccess().get());
    }

    @Test
    void registrationEmailUsingTest() {
        Mockito.when(userGateway.findByEmail("email@email.ru")).thenReturn(Optional.of(new User()));

        userInteract.setUserGateway(userGateway);
        Result<User, UserInteractError> result = userInteract.registration(userRegistrationDTO);

        Assertions.assertTrue(result.hasFailure());
        Assertions.assertTrue(result.getFailure().get() instanceof UserInteractError.EmailExists);
    }

    @Test
    void registrationPasswordNotConfirmTest() {
        Mockito.when(userGateway.findByEmail("email@email.ru")).thenReturn(Optional.empty());
        Mockito.when(userRegistrationDTO.confirmPassword()).thenReturn("noMatchPassword");

        userInteract.setUserGateway(userGateway);
        Result<User, UserInteractError> result = userInteract.registration(userRegistrationDTO);

        Assertions.assertTrue(result.hasFailure());
        Assertions.assertTrue(result.getFailure().get() instanceof UserInteractError.PasswordNotConfirm);
    }


}

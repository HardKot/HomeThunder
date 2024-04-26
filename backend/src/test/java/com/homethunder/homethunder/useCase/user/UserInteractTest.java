package com.homethunder.homethunder.useCase.user;

import com.homethunder.homethunder.domain.user.Gender;
import com.homethunder.homethunder.domain.user.IUserGateway;
import com.homethunder.homethunder.domain.user.User;
import com.homethunder.homethunder.useCase.user.dto.IUserRegistration;
import com.leakyabstractions.result.api.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.mockStatic;


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
    }


    @BeforeEach
    void generateExecute() {
        userExecute = new User();
        userExecute.setId(UUID.fromString("d5962259-2f54-4b8a-8ce8-19da2768be3d"));
        userExecute.setFirstname("Иван");
        userExecute.setLastname("Иванов");
        userExecute.setPatronymic("Иванович");
        userExecute.setPassword("password");
        userExecute.setEmail("email@email.ru");
        userExecute.setGender(Gender.Male);
        userExecute.setBirthday(LocalDate.of(2000, Month.JANUARY, 1));
    }

    @Test
    void registrationSuccessTest() {
        UUID mockUUID = UUID.fromString("d5962259-2f54-4b8a-8ce8-19da2768be3d");
        try (MockedStatic<UUID> mockedStatic = mockStatic(UUID.class)) {
            mockedStatic.when(UUID::randomUUID).thenReturn(mockUUID);

            Mockito.when(userGateway.findByEmail("email@email.ru")).thenReturn(Optional.empty());
            Mockito.when(userGateway.passwordEncoder("password")).thenReturn("password");
            Mockito.when(userGateway.create(Mockito.any(User.class))).thenReturn(null);
            Mockito.when(userGateway.generateTokenForEmail(Mockito.any(), Mockito.anyString())).thenReturn("token");

            userInteract.setUserGateway(userGateway);
            Result<User, UserInteractError> result = userInteract.registration(userRegistrationDTO);

            Assertions.assertTrue(result.hasSuccess());
            Assertions.assertEquals(userExecute, result.getSuccess().get());
        }
    }
}

package com.homethunder.homethunder.services;

import com.homethunder.homethunder.domain.user.Gender;
import com.homethunder.homethunder.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;


@SpringBootTest
@ActiveProfiles("test-containers-flyway")
@DataJpaTest
class UserServiceUnitTests {
    @TestConfiguration
    static class UserServiceUnitTestsContextConfiguration {
        @Bean
        public UserService userService() {
            return new UserService();
        }
    }

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserRuleDetailRepository userRuleRetailRepository;

    @MockBean
    private UserRoleDetailRepository userRoleDetailRepository;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Успешное создание пользователя")
    void registrationSuccess() {
        UserService.UserRegistrationFactory payload = new UserService.UserRegistrationFactory();

        payload.setFirstname("Иван");
        payload.setLastname("Иванов");
        payload.setPatronymic("Иванович");
        payload.setBirthday(LocalDate.of(1990, 1, 1));
        payload.setGender(Gender.Male);
        payload.setEmail("0V9lZ@example.com");
        payload.setPassword("123456");
        payload.setConfirmPassword("123456");
        payload.setPhone("1234567890");

        userService.registration(payload);
    }
}

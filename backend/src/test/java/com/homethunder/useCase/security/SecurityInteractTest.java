package com.homethunder.useCase.security;

import com.homethunder.domain.security.ISecurityGateway;
import com.homethunder.domain.security.Token;
import com.homethunder.domain.user.Role;
import com.homethunder.domain.user.User;
import com.homethunder.useCase.security.dto.ILoginDTO;
import com.leakyabstractions.result.api.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.*;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.mockStatic;

class SecurityInteractTest {

    private final SecurityInteract securityInteract = new SecurityInteract();
    private ISecurityGateway securityGateway;
    private Token testToken;
    private User testUser;

    private final String jwtExempla = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

    private final Clock clock = Clock.fixed(Instant.parse("2024-04-10T00:00:00Z"), ZoneId.of("UTC"));
    private final LocalDateTime localDateTime = LocalDateTime.now(clock);


    @BeforeEach
    void generateGateway() {
        securityGateway = Mockito.mock(ISecurityGateway.class);

        Mockito.when(securityGateway.generateJWT(Mockito.any(Token.class))).thenReturn(jwtExempla);
        Mockito.when(securityGateway.saveToken(Mockito.any(Token.class))).thenAnswer(i -> i.getArguments()[0]);
        Mockito.when(securityGateway.authenticateInManager(Mockito.any(User.class), Mockito.anyString())).thenReturn(true);
        Mockito.when(securityGateway.authenticateInManager(Mockito.any(User.class))).thenReturn(true);

        securityInteract.setSecurityGateway(securityGateway);
    }

    @BeforeEach
    void generateToken() {
        testToken = new Token();
        testToken.setUid(UUID.randomUUID());
        testToken.setIp("127.0.0.1");
        testToken.setRuleSet(Set.of());
        testToken.setDeviceName("Junit test");
    }

    @BeforeEach
    void generateUser() {
        testUser = new User();
    }

    @Test
    @Tag("refreshingToken")
    void refreshingNewTokenTest() {
        testToken.setCreateAt(LocalDateTime.of(2024, Month.APRIL, 5, 1, 0));
        try (MockedStatic<LocalDateTime> mockedStatic = mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(localDateTime);
            Result<String, SecurityInteractError> result = securityInteract.refreshingToken(testToken);

            Assertions.assertTrue(result.hasSuccess());
            Assertions.assertTrue(result.getSuccess().isPresent());
            Assertions.assertEquals(jwtExempla, result.getSuccess().get());
        }
    }

    @Test
    @Tag("refreshingToken")
    void refreshingOldTokenTest() {
        testToken.setCreateAt(LocalDateTime.of(2024, Month.APRIL, 1, 1, 0));
        try (MockedStatic<LocalDateTime> mockedStatic = mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(localDateTime);
            Result<String, SecurityInteractError> result = securityInteract.refreshingToken(testToken);

            Assertions.assertTrue(result.hasFailure());
            Assertions.assertTrue(result.getFailure().isPresent());
            Assertions.assertInstanceOf(SecurityInteractError.TokenIsNoRefreshing.class, result.getFailure().get());
        }
    }

    ILoginDTO loginDTO() {
        ILoginDTO dto = Mockito.mock(ILoginDTO.class);
        Mockito.when(dto.email()).thenReturn("email@email.ru");
        Mockito.when(dto.ip()).thenReturn("127.0.0.1");
        Mockito.when(dto.password()).thenReturn("password");
        Mockito.when(dto.deviceName()).thenReturn("Junit test");
        return dto;
    }

    @Test
    @Tag("login")
    void loginSuccessTest() {
        ILoginDTO dto = loginDTO();
        User user = new User();
        User.RoleDetail roleDetail = new User.RoleDetail();
        roleDetail.setRole(Role.Visitor);
        user.setRoleDetail(roleDetail);

        Mockito.when(securityGateway.findUserByEmail("email@email.ru")).thenReturn(Optional.of(user));

        Result<String, SecurityInteractError> result = securityInteract.login(dto);

        Assertions.assertTrue(result.hasSuccess());
        Assertions.assertTrue(result.getSuccess().isPresent());
        Assertions.assertEquals(jwtExempla, result.getSuccess().get());
    }

    @Test
    @Tag("login")
    void loginUserNoFoundTest() {
        ILoginDTO dto = loginDTO();
        Mockito.when(securityGateway.findUserByEmail("email@email.ru")).thenReturn(Optional.empty());

        Result<String, SecurityInteractError> result = securityInteract.login(dto);

        Assertions.assertTrue(result.hasFailure());
        Assertions.assertTrue(result.getFailure().isPresent());
        Assertions.assertInstanceOf(SecurityInteractError.EmailAndPasswordNoMatch.class, result.getFailure().get());
    }

    @Test
    @Tag("login")
    void loginPassportNoMatch() {
        ILoginDTO dto = loginDTO();
        Mockito.when(securityGateway.findUserByEmail("email@email.ru")).thenReturn(Optional.of(new User()));
        Mockito.when(securityGateway.authenticateInManager(Mockito.any(User.class), Mockito.anyString())).thenReturn(false);

        Result<String, SecurityInteractError> result = securityInteract.login(dto);

        Assertions.assertTrue(result.hasFailure());
        Assertions.assertTrue(result.getFailure().isPresent());
        Assertions.assertInstanceOf(SecurityInteractError.EmailAndPasswordNoMatch.class, result.getFailure().get());
    }

    @Test
    @Tag("getUserByJWT")
    void getUserByJWTSuccess() {
        UUID tokenID = UUID.randomUUID();
        UUID uid = testToken.getUid();

        Mockito.when(securityGateway.extractTokenID(jwtExempla)).thenReturn(tokenID);
        Mockito.when(securityGateway.findTokenById(tokenID)).thenReturn(Optional.of(testToken));
        Mockito.when(securityGateway.findUserByUID(uid)).thenReturn(Optional.of(testUser));
        Mockito.when(securityGateway.jwtIsExpired(jwtExempla)).thenReturn(false);
        Result<User, SecurityInteractError> result = securityInteract.authenticationByJWT(jwtExempla);

        Assertions.assertTrue(result.hasSuccess());
        Assertions.assertTrue(result.getSuccess().isPresent());
        Assertions.assertEquals(testUser, result.getSuccess().get());
        Mockito.verify(securityGateway, Mockito.times(1)).authenticateInManager(testUser);
    }

    @Test
    @Tag("getUserByJWT")
    void getUserByJWTIsExpired() {
        UUID tokenID = UUID.randomUUID();
        UUID uid = testToken.getUid();

        Mockito.when(securityGateway.jwtIsExpired(jwtExempla)).thenReturn(true);
        var result = securityInteract.authenticationByJWT(jwtExempla);

        Assertions.assertTrue(result.hasFailure());
        Assertions.assertTrue(result.getFailure().isPresent());
        Assertions.assertInstanceOf(SecurityInteractError.JWTExpired.class, result.getFailure().get());
    }


    @Test
    @Tag("getUserByJWT")
    void getUserByJWTNoFoundToken() {
        UUID tokenID = UUID.randomUUID();
        Mockito.when(securityGateway.extractTokenID(jwtExempla)).thenReturn(tokenID);
        Mockito.when(securityGateway.findTokenById(tokenID)).thenReturn(Optional.empty());

        Result<User, SecurityInteractError> result = securityInteract.authenticationByJWT(jwtExempla);

        Assertions.assertTrue(result.hasFailure());
        Assertions.assertTrue(result.getFailure().isPresent());
        Assertions.assertInstanceOf(SecurityInteractError.TokenNotExists.class, result.getFailure().get());
        Mockito.verify(securityGateway, Mockito.times(0)).authenticateInManager(Mockito.any(User.class));
    }

    @Test
    @Tag("getUsersByJWT")
    void getUserByJWTUserNoFound() {
        UUID tokenID = UUID.randomUUID();
        UUID uid = testToken.getUid();

        Mockito.when(securityGateway.extractTokenID(jwtExempla)).thenReturn(tokenID);
        Mockito.when(securityGateway.findTokenById(tokenID)).thenReturn(Optional.of(testToken));
        Mockito.when(securityGateway.findUserByUID(uid)).thenReturn(Optional.empty());

        var result = securityInteract.authenticationByJWT(jwtExempla);

        Assertions.assertTrue(result.hasFailure());
        Assertions.assertTrue(result.getFailure().isPresent());
        Assertions.assertInstanceOf(SecurityInteractError.UserNotExists.class, result.getFailure().get());
        Mockito.verify(securityGateway, Mockito.times(0)).authenticateInManager(Mockito.any(User.class));
    }


}

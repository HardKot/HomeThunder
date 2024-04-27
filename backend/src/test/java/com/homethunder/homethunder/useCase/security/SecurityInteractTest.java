package com.homethunder.homethunder.useCase.security;

import com.homethunder.homethunder.domain.security.ISecurityGateway;
import com.homethunder.homethunder.domain.security.Token;
import com.homethunder.homethunder.domain.user.Role;
import com.homethunder.homethunder.domain.user.User;
import com.homethunder.homethunder.useCase.security.dto.ILoginDTO;
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
import static org.mockito.Mockito.reset;

class SecurityInteractTest {

    private ISecurityGateway securityGateway;

    private final SecurityInteract securityInteract = new SecurityInteract();

    private Token testToken;

    private String jwtExempla = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

    private Clock clock = Clock.fixed(Instant.parse("2024-04-10T00:00:00Z"), ZoneId.of("UTC"));
    private LocalDateTime localDateTime = LocalDateTime.now(clock);

    @BeforeEach
    void generateGateway() {
        securityGateway = Mockito.mock(ISecurityGateway.class);

        Mockito.when(securityGateway.generateJWT(Mockito.any(Token.class))).thenReturn(jwtExempla);
        Mockito.when(securityGateway.save(Mockito.any(Token.class))).thenAnswer(i -> i.getArguments()[0]);
        Mockito.when(securityGateway.authenticateInManager(Mockito.any(User.class), Mockito.anyString())).thenReturn(true);


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
        testToken.setCreateAt(LocalDateTime.of(2024, Month.MAY, 5, 1, 0));
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

        Mockito.when(securityGateway.findByEmail("email@email.ru")).thenReturn(Optional.of(user));

        Result<String, SecurityInteractError> result = securityInteract.login(dto);

        Assertions.assertTrue(result.hasSuccess());
        Assertions.assertTrue(result.getSuccess().isPresent());
        Assertions.assertEquals(jwtExempla, result.getSuccess().get());
    }

    @Test
    @Tag("login")
    void loginUserNoFoundTest() {
        ILoginDTO dto = loginDTO();
        Mockito.when(securityGateway.findByEmail("email@email.ru")).thenReturn(Optional.empty());

        Result<String, SecurityInteractError> result = securityInteract.login(dto);

        Assertions.assertTrue(result.hasFailure());
        Assertions.assertTrue(result.getFailure().isPresent());
        Assertions.assertInstanceOf(SecurityInteractError.EmailAndPasswordNoMatch.class, result.getFailure().get());
    }

    @Test
    @Tag("login")
    void loginPassportNoMatch() {
        ILoginDTO dto = loginDTO();
        Mockito.when(securityGateway.findByEmail("email@email.ru")).thenReturn(Optional.of(new User()));
        Mockito.when(securityGateway.authenticateInManager(Mockito.any(User.class), Mockito.anyString())).thenReturn(false);

        Result<String, SecurityInteractError> result = securityInteract.login(dto);

        Assertions.assertTrue(result.hasFailure());
        Assertions.assertTrue(result.getFailure().isPresent());
        Assertions.assertInstanceOf(SecurityInteractError.EmailAndPasswordNoMatch.class, result.getFailure().get());
    }


}

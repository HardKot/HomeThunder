package com.homethunder.homethunder.useCase.staff;

import com.homethunder.homethunder.domain.staff.Employee;
import com.homethunder.homethunder.domain.user.Gender;
import com.homethunder.homethunder.domain.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

public class AddEmployeeUseCaseTest {
    private AddEmployeeUseCase.AddEmployeeDTO dto;

    private User user() {
        User user = new User();
        user.setFirstname("Иван");
        user.setLastname("Иванов");
        user.setPatronymic("Иванович");
        user.setPassword("password");
        user.setEmail("email@email.ru");
        user.setGender(Gender.Male);
        user.setBirthday(LocalDate.of(2000, Month.JANUARY, 1));
        return user;
    }

    private void generateEmployeeDTO() {
        dto = Mockito.mock(AddEmployeeUseCase.AddEmployeeDTO.class);
        Mockito.when(dto.positionID()).thenReturn(UUID.randomUUID());
    }

    private StaffGateway mockStaffGateway;

    @BeforeEach
    public void staffGateway() {
        mockStaffGateway = Mockito.mock(StaffGateway.class);
    }



    @Test
    public void testAddEmployee() {
        var result = new AddEmployeeUseCase().execute(user(), dto);

        Assertions.assertTrue(result.hasSuccess());
        Assertions.assertTrue(result.getSuccess().isPresent());
        Assertions.assertInstanceOf(Employee.class, result.getSuccess().get());
        Assertions.assertEquals(user().getId(), result.getSuccess().get().getId());
        Assertions.assertEquals("Начальник охраны", result.getSuccess().get().getPosition().getName());
    }

    @Test
    public void positionNotFound() {
        var result = new AddEmployeeUseCase().execute(user(), dto);

        Assertions.assertTrue(result.hasFailure());
        Assertions.assertTrue(result.getFailure().isPresent());
        Assertions.assertInstanceOf(AddEmployeeUseCase.Error.PositionNotFound.class, result.getFailure().get());
    }

    @Test
    public void userIsBanned() {
        var result = new AddEmployeeUseCase().execute(user(), dto);

        Assertions.assertTrue(result.hasFailure());
        Assertions.assertTrue(result.getFailure().isPresent());
        Assertions.assertInstanceOf(AddEmployeeUseCase.Error.UserIsBanned.class, result.getFailure().get());
    }

    @Test
    public void userIsDeleted() {
        var result = new AddEmployeeUseCase().execute(user(), dto);

        Assertions.assertTrue(result.hasFailure());
        Assertions.assertTrue(result.getFailure().isPresent());
        Assertions.assertInstanceOf(AddEmployeeUseCase.Error.UserIsDeleted.class, result.getFailure().get());
    }

    @Test
    public void userIsFired() {
        var result = new AddEmployeeUseCase().execute(user(), dto);

        Assertions.assertTrue(result.hasFailure());
        Assertions.assertTrue(result.getFailure().isPresent());
        Assertions.assertInstanceOf(AddEmployeeUseCase.Error.UserIsDeleted.class, result.getFailure().get());
    }

    @Test
    public void userIsWillDeleted() {
        var result = new AddEmployeeUseCase().execute(user(), dto);

        Assertions.assertTrue(result.hasSuccess());
        Assertions.assertTrue(result.getSuccess().isPresent());
        Assertions.assertInstanceOf(Employee.class, result.getSuccess().get());
        Assertions.assertEquals(user().getId(), result.getSuccess().get().getId());
        Assertions.assertEquals("Начальник охраны", result.getSuccess().get().getPosition().getName());
        Assertions.assertNull(result.getSuccess().get().getDeletedAt());
    }
}

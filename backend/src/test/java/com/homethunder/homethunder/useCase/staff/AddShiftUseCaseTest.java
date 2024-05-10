package com.homethunder.homethunder.useCase.staff;

import com.homethunder.homethunder.domain.staff.Employee;
import com.homethunder.homethunder.domain.staff.Shift;
import com.homethunder.homethunder.domain.user.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.*;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mockStatic;

public class AddShiftUseCaseTest {
    private AddShiftUseCase.AddShiftDTO dto;

    private Employee employee() {
        Employee employee = new Employee();
        employee.setFirstname("Иван");
        employee.setLastname("Иванов");
        employee.setPatronymic("Иванович");
        employee.setPassword("password");
        employee.setEmail("email@email.ru");
        employee.setGender(Gender.Male);
        employee.setBirthday(LocalDate.of(2000, Month.JANUARY, 1));
        return employee;
    }

    private final Clock mockClock = Clock.fixed(Instant.parse("2024-04-12T00:00:00Z"), ZoneId.of("UTC"));
    private final LocalDateTime mockLocalDateTime = LocalDateTime.now(mockClock);

    private static final record Break(LocalDateTime start, LocalDateTime end, String comment) implements Shift.IBreak { }

    @BeforeEach
    public void generateEmployeeDTO() {
        dto = Mockito.mock(AddShiftUseCase.AddShiftDTO.class);
        Mockito.when(dto.start()).thenReturn(LocalDateTime.of(2024, Month.APRIL, 13, 10, 0));
        Mockito.when(dto.end()).thenReturn(LocalDateTime.of(2024, Month.APRIL, 13, 19, 0));
        Mockito.when(dto.breaks()).thenReturn(List.of(new Break(
                LocalDateTime.of(2024, Month.APRIL, 13, 13, 0),
                LocalDateTime.of(2024, Month.APRIL, 13, 14, 0),
                "Обед"
        )));
        Mockito.when(dto.comment()).thenReturn(null);
    }

    private StaffGateway mockStaffGateway;

    private AddShiftUseCase addShiftUseCase;

    @BeforeEach
    public void staffGateway() {
        mockStaffGateway = Mockito.mock(StaffGateway.class);
        Mockito.when(mockStaffGateway.getShiftsByEmployee()).thenReturn(Optional.empty());
    }

    @BeforeEach
    public void setAddShiftUseCase() {
        addShiftUseCase = new AddShiftUseCase();
    }

    @Test
    public void testAddShift() {
        try (MockedStatic<LocalDateTime> mockedStatic = mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(mockLocalDateTime);
            var result = addShiftUseCase.execute(employee(), dto);

            Assertions.assertTrue(result.hasSuccess());
            Assertions.assertTrue(result.getSuccess().isPresent());
            Assertions.assertInstanceOf(Shift.class, result.getSuccess().get());
            Assertions.assertEquals(employee().getId(), result.getSuccess().get().getEmployee().getId());
            Assertions.assertEquals(
                    LocalDateTime.of(2024, Month.APRIL, 13, 13, 0),
                    result.getSuccess().get().getStart());
            Assertions.assertEquals(
                    LocalDateTime.of(2024, Month.APRIL, 13, 14, 0),
                    result.getSuccess().get().getEnd());
            Assertions.assertEquals(1, result.getSuccess().get().getBreaks().size());
        }
    }

    @Test
    public void startShiftInPast() {
        Mockito.when(dto.start()).thenReturn(LocalDateTime.of(2024, Month.APRIL, 12, 9, 0));
        try (MockedStatic<LocalDateTime> mockedStatic = mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(mockLocalDateTime);

            var result = addShiftUseCase.execute(employee(), dto);

            Assertions.assertTrue(result.hasFailure());
            Assertions.assertTrue(result.getFailure().isPresent());
            Assertions.assertInstanceOf(AddShiftUseCase.AddShiftError.StartShiftInPast.class, result.getFailure().get());
        }
    }

    @Test
    public void endShiftBeforeStart() {
        Mockito.when(dto.start()).thenReturn(LocalDateTime.of(2024, Month.APRIL, 13, 13, 0));
        try (MockedStatic<LocalDateTime> mockedStatic = mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(mockLocalDateTime);

            var result = addShiftUseCase.execute(employee(), dto);

            Assertions.assertTrue(result.hasFailure());
            Assertions.assertTrue(result.getFailure().isPresent());
            Assertions.assertInstanceOf(AddShiftUseCase.AddShiftError.ShiftEndTimeBeforeShiftStart.class, result.getFailure().get());
        }
    }

    @Test
    public void userIsFiredInBeforeShift() {
        var mockEmployee = employee();
        mockEmployee.setDateOfFired(LocalDate.of(2024, Month.APRIL, 12));

        try (MockedStatic<LocalDateTime> mockedStatic = mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(mockLocalDateTime);

            var result = addShiftUseCase.execute(employee(), dto);

            Assertions.assertTrue(result.hasFailure());
            Assertions.assertTrue(result.getFailure().isPresent());
            Assertions.assertInstanceOf(AddShiftUseCase.AddShiftError.UserIsFired.class,result.getFailure().get());
        }
    }
    @Test
    public void userIsFiredInShift() {
        var mockEmployee = employee();
        mockEmployee.setDateOfFired(LocalDate.of(2024, Month.APRIL, 13));
        Mockito.when(dto.end()).thenReturn(LocalDateTime.of(2024, Month.APRIL, 14, 10, 0));

        try (MockedStatic<LocalDateTime> mockedStatic = mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(mockLocalDateTime);

            var result = addShiftUseCase.execute(employee(), dto);

            Assertions.assertTrue(result.hasFailure());
            Assertions.assertTrue(result.getFailure().isPresent());
            Assertions.assertInstanceOf(AddShiftUseCase.AddShiftError.UserIsFired.class,result.getFailure().get());
        }
    }

    @Test
    public void userIsFiredInDayShift() {
        var mockEmployee = employee();
        mockEmployee.setDateOfFired(LocalDate.of(2024, Month.APRIL, 13));

        try (MockedStatic<LocalDateTime> mockedStatic = mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(mockLocalDateTime);

            var result = addShiftUseCase.execute(employee(), dto);

            Assertions.assertTrue(result.hasSuccess());
            Assertions.assertTrue(result.getSuccess().isPresent());
            Assertions.assertInstanceOf(Shift.class, result.getSuccess().get());
            Assertions.assertEquals(employee().getId(), result.getSuccess().get().getEmployee().getId());
            Assertions.assertEquals(
                    LocalDateTime.of(2024, Month.APRIL, 13, 13, 0),
                    result.getSuccess().get().getStart());
            Assertions.assertEquals(
                    LocalDateTime.of(2024, Month.APRIL, 13, 14, 0),
                    result.getSuccess().get().getEnd());
            Assertions.assertEquals(1, result.getSuccess().get().getBreaks().size());
        }
    }

    @Test
    public void offBreakBeginnerShift() {
        Mockito.when(dto.breaks()).thenReturn(List.of(new Break(
            LocalDateTime.of(2024, Month.APRIL, 13, 9, 0),
            LocalDateTime.of(2024, Month.APRIL, 13, 11, 0),
            null)));
        try (MockedStatic<LocalDateTime> mockedStatic = mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(mockLocalDateTime);

            var result = addShiftUseCase.execute(employee(), dto);

            Assertions.assertTrue(result.hasFailure());
            Assertions.assertTrue(result.getFailure().isPresent());
            Assertions.assertInstanceOf(AddShiftUseCase.AddShiftError.OffBreakShift.class,result.getFailure().get());
        }
    }

    @Test
    public void offBreakEndShift() {
        Mockito.when(dto.breaks()).thenReturn(List.of(new Break(
            LocalDateTime.of(2024, Month.APRIL, 13, 18, 0),
            LocalDateTime.of(2024, Month.APRIL, 13, 20, 0),
            null)));
        try (MockedStatic<LocalDateTime> mockedStatic = mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(mockLocalDateTime);

            var result = addShiftUseCase.execute(employee(), dto);

            Assertions.assertTrue(result.hasFailure());
            Assertions.assertTrue(result.getFailure().isPresent());
            Assertions.assertInstanceOf(AddShiftUseCase.AddShiftError.OffBreakShift.class,result.getFailure().get());
        }
    }

    @Test
    public void offBreakAllShift() {
        Mockito.when(dto.breaks()).thenReturn(List.of(new Break(
            LocalDateTime.of(2024, Month.APRIL, 13, 20, 0),
            LocalDateTime.of(2024, Month.APRIL, 13, 21, 0),
            null)));
        try (MockedStatic<LocalDateTime> mockedStatic = mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(mockLocalDateTime);

            var result = addShiftUseCase.execute(employee(), dto);

            Assertions.assertTrue(result.hasFailure());
            Assertions.assertTrue(result.getFailure().isPresent());
            Assertions.assertInstanceOf(AddShiftUseCase.AddShiftError.OffBreakShift.class,result.getFailure().get());
        }
    }

    @Test
    public void employeeShiftOverlap() {
        var shift = new Shift();
        shift.setStart(LocalDateTime.of(2024, Month.APRIL, 13, 6, 0));
        shift.setEnd(LocalDateTime.of(2024, Month.APRIL, 13, 15, 0));
        shift.setEmployee(employee());

        Mockito.when(mockStaffGateway.getShiftsByEmployee()).thenReturn(Optional.of(
                List.of(shift)
        ));
        try (MockedStatic<LocalDateTime> mockedStatic = mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(mockLocalDateTime);

            var result = addShiftUseCase.execute(employee(), dto);

            Assertions.assertTrue(result.hasFailure());
            Assertions.assertTrue(result.getFailure().isPresent());
            Assertions.assertInstanceOf(AddShiftUseCase.AddShiftError.EmployeeShiftOverlap.class,result.getFailure().get());
        }
    }

    @Test
    public void breakEndBeforeBreakStart() {
        Mockito.when(dto.breaks()).thenReturn(List.of(new Break(
                LocalDateTime.of(2024, Month.APRIL, 13, 14, 0),
                LocalDateTime.of(2024, Month.APRIL, 13, 13, 0),
                null)));
        try (MockedStatic<LocalDateTime> mockedStatic = mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(mockLocalDateTime);

            var result = addShiftUseCase.execute(employee(), dto);

            Assertions.assertTrue(result.hasFailure());
            Assertions.assertTrue(result.getFailure().isPresent());
            Assertions.assertInstanceOf(AddShiftUseCase.AddShiftError.ShiftBreakEndBeforeShiftBreakStart.class,result.getFailure().get());
        }
    }

}

package com.homethunder.domain.staff;

import com.homethunder.domain.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class Employee extends User {
    private EmployeePosition position;
    private LocalDate dateOfFired;
}

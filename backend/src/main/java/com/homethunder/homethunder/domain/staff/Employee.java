package com.homethunder.homethunder.domain.staff;

import com.homethunder.homethunder.domain.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Employee extends User {
    private EmployeePosition position;
}

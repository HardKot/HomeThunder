package com.homethunder.homethunder.domain.staff;

import com.homethunder.homethunder.domain.BaseEntity;
import com.homethunder.homethunder.domain.user.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeePosition extends BaseEntity {
    private String name;

    private String description;
    private Role role;

    private UUID imageId;
}

package com.homethunder.domain.service;

import com.homethunder.domain.BaseEntity;
import com.homethunder.domain.staff.EmployeePosition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceCategory extends BaseEntity {
    private String name;
    private String description;

    private UUID image;
    private Set<EmployeePosition> executors;


}

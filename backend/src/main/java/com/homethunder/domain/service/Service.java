package com.homethunder.domain.service;

import com.homethunder.domain.BaseEntity;
import com.homethunder.domain.staff.EmployeePosition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class Service extends BaseEntity {
    private String name;
    private String description;
    private UUID image;

    private Integer price;
    private Set<EmployeePosition> executors;

    private ServiceCategory category;

    public Set<EmployeePosition> getExecutors() {
        Set<EmployeePosition> executors = new HashSet<>(this.executors);
        if (Objects.nonNull(category)) executors.addAll(category.getExecutors());
        return executors;
    }
}

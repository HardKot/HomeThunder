package com.homethunder.domain.staff;

import com.homethunder.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Set;


@Data
@EqualsAndHashCode(callSuper = true)
public class StaffTask extends BaseEntity {
    private String title;
    private String description;

    private LocalDateTime deadline;
    private LocalDateTime launch;

    private Set<Employee> executors;
    private String comment;

    private StaffTaskResult result;
    private Integer priority;
}

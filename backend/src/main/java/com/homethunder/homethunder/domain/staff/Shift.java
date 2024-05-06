package com.homethunder.homethunder.domain.staff;

import com.homethunder.homethunder.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class Shift extends BaseEntity {
    private Employee employee;
    private LocalDateTime start;
    private LocalDateTime end;

    private String comment;

}

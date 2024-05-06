package com.homethunder.homethunder.domain.staff;

import com.homethunder.homethunder.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
public class WorkSchedule extends BaseEntity {
    private LocalDate date;
    private Employee employee;

    private List<WorkSchedule> workTime;

    private String comment;

    public interface WorkScheduleTime {
        LocalTime start();
        LocalTime end();
        String comment();
    }
}

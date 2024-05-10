package com.homethunder.homethunder.domain.staff;

import com.homethunder.homethunder.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Shift extends BaseEntity {
    private Employee employee;
    private LocalDateTime start;
    private LocalDateTime end;
    private List<IBreak> breaks;

    private String comment;

    public interface IBreak {
        LocalDateTime start();
        LocalDateTime end();
        String comment();
    }
}

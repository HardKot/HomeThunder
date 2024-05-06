package com.homethunder.homethunder.domain.resident;

import com.homethunder.homethunder.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class Apartment extends BaseEntity {
    private String number;

    private String comment;
}

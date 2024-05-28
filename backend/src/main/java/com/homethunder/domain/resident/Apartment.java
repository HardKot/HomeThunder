package com.homethunder.domain.resident;

import com.homethunder.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class Apartment extends BaseEntity {
    private String number;

    private String comment;
}

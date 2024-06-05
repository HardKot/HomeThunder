package com.homethunder.domain.realEstate;


import com.homethunder.domain.BaseEntity;
import com.homethunder.domain.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RealEstate extends BaseEntity {
    private String city;
    private String address;

    private User owner;
}

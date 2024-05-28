package com.homethunder.domain.resident;

import com.homethunder.domain.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class Residents extends User {
    private Apartment home;
}

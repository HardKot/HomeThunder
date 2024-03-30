package com.homethunder.homethunder.domain.user;

public class Owner extends User {
    @Override
    public Role getRole() {
        return new OwnerRole();
    }
}

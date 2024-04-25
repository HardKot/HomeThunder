package com.homethunder.homethunder.domain.user;

import java.util.Optional;

public interface IRoleGateway {

    Role create(Role role);

    Role update(Role role);

    Role delete(Role role);

    void forseDelete(Role role);

    Optional<Role> findByName(String name);

}

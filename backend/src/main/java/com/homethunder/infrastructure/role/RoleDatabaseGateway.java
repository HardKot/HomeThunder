package com.homethunder.infrastructure.role;


import com.homethunder.domain.user.IRoleGateway;
import com.homethunder.domain.user.Role;
import com.homethunder.infrastructure.db.repository.RoleRepository;
import com.homethunder.infrastructure.db.schema.RoleSchema;

import java.time.LocalDateTime;
import java.util.Optional;


public class RoleDatabaseGateway implements IRoleGateway {
    private final RoleRepository roleRepository;

    public RoleDatabaseGateway(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role create(Role user) {
        return this.roleRepository.save(new RoleSchema(user)).toRole();
    }

    @Override
    public Role update(Role role) {
        RoleSchema roleSchema = new RoleSchema(role);
        roleSchema.setUpdatedAt(LocalDateTime.now());

        return this.roleRepository.save(roleSchema).toRole();
    }

    @Override
    public Role delete(Role role) {
        RoleSchema roleSchema = new RoleSchema(role);
        roleSchema.setDeletedAt(LocalDateTime.now());

        return this.roleRepository.save(roleSchema).toRole();
    }

    @Override
    public void forseDelete(Role role) {
        this.roleRepository.delete(new RoleSchema(role));
    }

    @Override
    public Optional<Role> findByName(String name) {
        return this.roleRepository.findByName(name).map(RoleSchema::toRole);
    }

}

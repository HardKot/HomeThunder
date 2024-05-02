package com.homethunder.homethunder.infrastructure.db.schema;


import com.homethunder.homethunder.domain.Rule;
import com.homethunder.homethunder.domain.user.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "app_roles")
public class RoleSchema extends BaseSchema {
    private String name;
    private String image;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "role_rules", joinColumns = @JoinColumn(name = "role_id"))
    @Column(name = "rule")
    @Enumerated(EnumType.STRING)
    private Set<Rule> ruleDetailSet = Set.of();

    public RoleSchema() {
    }

    public RoleSchema(Role entity) {
        useBaseEntity(entity);

        name = entity.getName();
        image = entity.getImage();

        ruleDetailSet = entity.getRules();
    }

    public Role toRole() {
        Role role = getBaseEntity(new Role());

        role.setName(name);
        role.setImage(image);

        role.setRules(ruleDetailSet);

        return role;
    }
}

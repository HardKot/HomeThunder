package com.homethunder.homethunder.domain.user;

import com.homethunder.homethunder.domain.BaseEntity;
import com.homethunder.homethunder.domain.Rule;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class Role extends BaseEntity {
    static public Role Owner = new Role("Owner");
    static public Role Visitor = new Role("Visitor");
    private String name;
    private String image;
    private Set<Rule> rules;

    public Role() {
    }

    private Role(String name) {

    }

    public boolean getAccess(Rule rule) {
        return rules.contains(rule);
    }
}

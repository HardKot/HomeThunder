package com.homethunder.domain.user;

import com.homethunder.domain.BaseEntity;
import com.homethunder.domain.Rule;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Role extends BaseEntity {
    static public Role Owner = new Role();
    static public Role Visitor = new Role();

    private String name;
    private String image;
    private Set<Rule> rules;

    public boolean getAccess(Rule rule) {
        return rules.contains(rule);
    }
}

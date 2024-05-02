package com.homethunder.homethunder.domain.user;

import com.homethunder.homethunder.domain.BaseEntity;
import com.homethunder.homethunder.domain.Rule;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Data
public class User extends BaseEntity {
    private String firstname;
    private String lastname;
    private String patronymic;
    private String avatarURI;
    private Gender gender;
    private LocalDate birthday;
    private String phone;
    private String email;
    private String password;
    private LocalDateTime bannedBefore;
    private Set<RuleDetail> ruleDetailSet = Set.of();
    private RoleDetail roleDetail;
    private boolean isActivate;

    public Role getRole() {
        if (roleDetail.isActive()) return roleDetail.getRole();
        return Role.Visitor;
    }

    public Set<Rule> getActiveRule() {
        Set<Rule> ruleSet = getRole().getRules();

        for (RuleDetail rule : ruleDetailSet) {
            if (rule.isActive()) ruleSet.add(rule.rule);
        }

        return ruleSet;
    }

    @Data
    public static class RuleDetail {
        private Rule rule;
        private LocalDateTime activateEnd;

        public boolean isActive() {
            if (activateEnd == null) {
                return true;
            }
            return activateEnd.isBefore(LocalDateTime.now());
        }
    }

    @Data
    public static class RoleDetail {
        private Role role;
        private LocalDateTime activateTo;

        public boolean isActive() {
            if (activateTo == null) {
                return true;
            }
            return activateTo.isBefore(LocalDateTime.now());
        }
    }

}

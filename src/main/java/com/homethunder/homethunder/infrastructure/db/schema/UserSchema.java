package com.homethunder.homethunder.infrastructure.db.schema;

import com.homethunder.homethunder.domain.Rule;
import com.homethunder.homethunder.domain.user.Gender;
import com.homethunder.homethunder.domain.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "app_users")
@SecondaryTable(name = "user_roles", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
@SecondaryTable(name = "user_email", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
public class UserSchema extends BaseSchema {
    private String firstname;
    private String lastname;
    private String patronymic;
    private String avatarURI;
    private Gender gender;
    private LocalDate birthday;
    private String phone;

    private String password;
    private LocalDateTime bannedBefore;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_rules", joinColumns = @JoinColumn(name = "user_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "rule", column = @Column(name = "rule")),
            @AttributeOverride(name = "activateTo", column = @Column(name = "activate_to"))
    })
    private Set<RuleDetailSchema> ruleDetailSet = Set.of();


//    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
//    @AttributeOverrides({
//            @AttributeOverride(name = "role", column = @Column(name = "role_id")),
//            @AttributeOverride(name = "activateTo", column = @Column(name = "activate_to"))
//    })
//    private RoleDetailSchema roleDetail;

    @Column(name = "email", table = "user_email")
    private String email;

    @Column(name = "is_activate", table = "user_email")
    private boolean isActivate;

    public UserSchema() {}

    public UserSchema(User entity) {
        useBaseEntity(entity);

        firstname     = entity.getFirstname();
        lastname      = entity.getLastname();
        patronymic    = entity.getPatronymic();

        avatarURI     = entity.getAvatarURI();
        gender        = entity.getGender();
        birthday      = entity.getBirthday();

        phone         = entity.getPhone();
        email         = entity.getEmail();

        password      = entity.getPassword();
        bannedBefore  = entity.getBannedBefore();

        ruleDetailSet = entity.getRuleDetailSet().stream().map(RuleDetailSchema::new).collect(Collectors.toSet());
//        roleDetail    = new RoleDetailSchema(entity.getRoleDetail());

    }

    public User toUser() {
        User user = getBaseEntity(new User());

        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPatronymic(patronymic);

        user.setAvatarURI(avatarURI);
        user.setGender(gender);
        user.setBirthday(birthday);

        user.setPhone(phone);
        user.setEmail(email);

        user.setPassword(password);
        user.setBannedBefore(bannedBefore);

//        user.setRoleDetail(roleDetail.toRoleDetail());


        return user;
    }

    @Embeddable
    @Data
    public static class RuleDetailSchema {
        private Rule rule;
        private LocalDateTime activateEnd;

        public RuleDetailSchema() {}

        public RuleDetailSchema(User.RuleDetail ruleDetail) {
            rule        = ruleDetail.getRule();
            activateEnd = ruleDetail.getActivateEnd();
        }

        public boolean isActive() {
            if (activateEnd == null) {
                return true;
            }
            return activateEnd.isBefore(LocalDateTime.now());
        }
    }

    @Embeddable
    @Data
    public static class RoleDetailSchema {
        private RoleSchema role;
        private LocalDateTime activateEnd;

        public RoleDetailSchema() {}

        public RoleDetailSchema(User.RoleDetail roleDetail) {
            role        = new RoleSchema(roleDetail.getRole());
            activateEnd = roleDetail.getActivateTo();
        }

        public boolean isActive() {
            if (activateEnd == null) {
                return true;
            }
            return activateEnd.isBefore(LocalDateTime.now());
        }

        public User.RoleDetail toRoleDetail() {
            User.RoleDetail roleDetail = new User.RoleDetail();

            roleDetail.setRole(role.toRole());
            roleDetail.setActivateTo(activateEnd);

            return roleDetail;
        }
    }
}

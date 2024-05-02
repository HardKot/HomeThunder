package com.homethunder.homethunder.infrastructure.db.schema;


import com.homethunder.homethunder.domain.security.Token;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "token")
@Entity
@Data
@NoArgsConstructor
public class TokenSchema {
    @Column(name = "created_at")
    @CreatedDate
    protected LocalDateTime createdAt;
    @Id
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserSchema user;
    @Column(name = "device_name")
    private String deviceName;
    private String ip;

    public static TokenSchema build(Token token, UserSchema userSchema) {
        TokenSchema tokenSchema = new TokenSchema();
        tokenSchema.setId(token.getId());
        tokenSchema.setIp(token.getIp());
        tokenSchema.setCreatedAt(token.getCreateAt());
        tokenSchema.setDeviceName(token.getDeviceName());
        tokenSchema.setUser(userSchema);

        return tokenSchema;
    }

    public Token toToken() {
        Token token = new Token();
        token.setId(id);
        token.setDeviceName(deviceName);
        token.setIp(ip);
        token.setUid(user.getId());
        token.setCreateAt(createdAt);
        token.setRuleSet(user.toUser().getActiveRule());

        return token;
    }
}

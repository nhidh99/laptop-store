package org.example.model.entity.token;

import org.example.constant.TokenConstants;
import org.example.model.entity.user.User;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Entity
@Table(name = "confirmation_token")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "token_type")
public abstract class ConfirmationToken {
    @Value("${org.example.server.hostname:error_hostname}")
    private String hostname;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "created_at", nullable = false)
    protected LocalDateTime createdAt;

    @Column(name = "expired_at", nullable = false)
    protected LocalDateTime expiredAt;

    @Column(name = "token_string", nullable = false, length = 1000)
    protected String tokenString;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    protected User user;

    @Transient
    protected String pathname;

    public String getConfirmationUrl() {
        return String.format("%s/%s?token=%s", hostname, pathname, tokenString);
    }

    @PrePersist
    public void prePersist() {
        this.tokenString = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now(ZoneOffset.UTC);
        this.expiredAt = this.createdAt.plusMinutes(TokenConstants.VERIFY_TOKEN_EXPIRATION_IN_MINUTE);
    }
}

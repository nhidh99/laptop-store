package org.example.model.entity.user;

import lombok.Builder;
import lombok.Data;
import org.example.model.entity.cart.Cart;
import org.example.model.entity.token.ConfirmationToken;
import org.example.model.request.CreateUserRequest;
import org.example.model.type.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;

@Entity
@Table(name = "\"user\"")
@Data
@Builder
public class User {
    private static final PasswordEncoder pwEncoder = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "username", length = 20, unique = true)
    private String username;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "role", length = 10)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "record_status")
    private boolean recordStatus;

    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "user")
    private Cart cart;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private UserDetail detail;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private UserLink link;

    @OneToMany(mappedBy = "user")
    private Set<ConfirmationToken> tokens;

    public static User fromCreateUserRequest(CreateUserRequest request) {
        String hashedPassword = pwEncoder.encode(request.password());
        return User.builder()
            .username(request.username())
            .recordStatus(true)
            .role(UserRole.CUSTOMER)
            .password(hashedPassword).build();
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now(ZoneOffset.UTC);
    }
}

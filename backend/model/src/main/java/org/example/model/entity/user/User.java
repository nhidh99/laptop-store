package org.example.model.entity.user;

import lombok.*;
import org.example.model.entity.base.BaseEntity;
import org.example.model.entity.token.ConfirmationToken;
import org.example.model.request.CreateUserRequest;
import org.example.model.type.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "`user`")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    private static final PasswordEncoder pwEncoder = new BCryptPasswordEncoder();

    @Column(name = "username", length = 20, unique = true)
    private String username;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "role", length = 10)
    @Enumerated(EnumType.STRING)
    private UserRole role;

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
                .role(UserRole.CUSTOMER)
                .password(hashedPassword).build();
    }
}

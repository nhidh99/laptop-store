package org.example.model.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.entity.base.BaseEntity;
import org.example.model.entity.token.ConfirmationToken;
import org.example.model.request.login.FacebookLoginRequest;
import org.example.model.request.user.CreateUserRequest;
import org.example.model.type.Gender;
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

    @Column(name = "facebook_id", length = 30)
    private String facebookId;

    @Column(name = "google_id", length = 30)
    private String googleId;

    @Column(name = "pending_email", length = 100)
    private String pendingEmail;

    @Column(name = "verified_email", length = 100)
    private String verifiedEmail;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "phone", length = 10)
    private String phone;

    @Column(name = "gender", length = 10)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "user")
    private Set<ConfirmationToken> tokens;

    public static User fromCreateUserRequest(CreateUserRequest request) {
        String hashedPassword = pwEncoder.encode(request.password());
        return User.builder()
                .username(request.username())
                .pendingEmail(request.email())
                .name(request.name())
                .phone(request.phone())
                .gender(request.gender())
                .role(UserRole.CUSTOMER)
                .password(hashedPassword).build();
    }

    public static User fromFacebookLoginRequest(FacebookLoginRequest request) {
        return User.builder()
                .username("fb|" + request.userId())
                .password("fb|password")
                .name(request.name())
                .verifiedEmail(request.email())
                .role(UserRole.CUSTOMER)
                .facebookId(request.userId()).build();
    }
}

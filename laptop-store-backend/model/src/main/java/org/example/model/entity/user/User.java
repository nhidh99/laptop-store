package org.example.model.entity.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.model.entity.token.ConfirmationToken;
import org.example.model.type.UserRole;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "\"user\"")
@Data
public class User {
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

    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "user")
    private UserDetail detail;

    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "user")
    private UserLink link;

    @OneToMany(mappedBy = "user")
    private Set<ConfirmationToken> tokens;
}

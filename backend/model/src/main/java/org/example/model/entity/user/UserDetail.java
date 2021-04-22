package org.example.model.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.request.CreateUserRequest;
import org.example.model.type.Gender;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_detail")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetail implements Serializable {
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

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

    @Column(name = "address_id")
    private Long addressId;

    public static UserDetail fromCreateUserRequest(CreateUserRequest request) {
        return UserDetail.builder()
            .name(request.name())
            .phone(request.phone())
            .pendingEmail(request.email())
            .gender(request.gender()).build();
    }
}

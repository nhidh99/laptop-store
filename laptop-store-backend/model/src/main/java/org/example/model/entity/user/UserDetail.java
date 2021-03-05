package org.example.model.entity.user;

import lombok.Data;
import org.example.model.type.Gender;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_detail")
@Data
public class UserDetail implements Serializable {
    @Id
    @OneToOne
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

    @Column(name = "cart", length = 1000, columnDefinition = "json")
    private String cart;

    @Column(name = "wish_list", length = 1000, columnDefinition = "json")
    private String wishList;

    @Column(name = "address_id")
    private Long addressId;
}

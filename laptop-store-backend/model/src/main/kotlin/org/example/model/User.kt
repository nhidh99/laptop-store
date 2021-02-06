package org.example.model

import org.example.type.Gender
import org.example.type.UserRole
import javax.persistence.*

@Entity
@Table(name = "user")
data class User(
    @Id @GeneratedValue
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "username")
    var username: String? = null,

    @Column(name = "password")
    var password: String? = null,

    @Column(name = "email")
    var email: String? = null,

    @Column(name = "name")
    var name: String? = null,

    @Column(name = "phone")
    var phone: String? = null,

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    var role: UserRole? = null,

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    var gender: Gender? = null,

    @Column(name = "cart")
    var cart: String? = null,

    @Column(name = "wish_list")
    var wishList: String? = null,

    @Column(name = "facebook_id")
    var facebookId: String? = null,

    @Column(name = "google_id")
    var googleId: String? = null,

    @Column(name = "address_id")
    var addressId: Long? = null,
)
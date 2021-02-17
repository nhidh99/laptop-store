package org.example.model.entity.user

import org.example.model.type.Gender
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "user_detail")
data class UserDetail(
    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    var user: User,

    @Column(name = "email", length = 50)
    var email: String? = null,

    @Column(name = "name", length = 30)
    var name: String? = null,

    @Column(name = "phone", length = 10)
    var phone: String? = null,

    @Column(name = "gender", length = 10)
    @Enumerated(EnumType.STRING)
    var gender: Gender? = null,

    @Column(name = "cart", length = 1000, columnDefinition = "json")
    var cart: String? = null,

    @Column(name = "wish_list", length = 1000, columnDefinition = "json")
    var wishList: String? = null,

    @Column(name = "address_id")
    var addressId: Long? = null,
) : Serializable {
    companion object {
        private const val serialVersionUID: Long = 1L
    }
}
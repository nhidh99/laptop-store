package org.example.model.entity.user

import org.example.model.type.Gender
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "user_detail")
class UserDetail : Serializable {
    companion object {
        private const val serialVersionUID: Long = 1L
    }

    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    var user: User? = null

    @Column(name = "pending_email", length = 100)
    var pendingEmail: String? = null

    @Column(name = "verified_email", length = 100)
    var verifiedEmail: String? = null

    @Column(name = "name", length = 30)
    var name: String? = null

    @Column(name = "phone", length = 10)
    var phone: String? = null

    @Column(name = "gender", length = 10)
    @Enumerated(EnumType.STRING)
    var gender: Gender? = null

    @Column(name = "cart", length = 1000, columnDefinition = "json")
    var cart: String? = null

    @Column(name = "wish_list", length = 1000, columnDefinition = "json")
    var wishList: String? = null

    @Column(name = "address_id")
    var addressId: Long? = null
}
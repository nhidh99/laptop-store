package org.example.model.entity.user

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "user_link")
class UserLink : Serializable {
    companion object {
        private const val serialVersionUID: Long = 1L
    }

    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    val user: User? = null

    @Column(name = "facebook_id", length = 30)
    val facebookId: String? = null

    @Column(name = "google_id", length = 30)
    val googleId: String? = null
}
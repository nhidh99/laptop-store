package org.example.model.entity.user

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "user_link")
data class UserLink(
    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    var user: User,

    @Column(name = "facebook_id", length = 30)
    var facebookId: String? = null,

    @Column(name = "google_id", length = 30)
    var googleId: String? = null,
) : Serializable {
    companion object {
        private const val serialVersionUID: Long = 1L
    }
}
package org.example.model.entity.user

import org.example.model.type.UserRole
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "\"user\"")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    var id: Long? = null,

    @Column(name = "username", length = 20, unique = true)
    var username: String? = null,

    @Column(name = "password", length = 100)
    var password: String? = null,

    @Column(name = "role", length = 10)
    @Enumerated(EnumType.STRING)
    var role: UserRole? = null,

    @Column(name = "created_at")
    var createdAt : LocalDateTime?,

    @OneToOne(cascade = [CascadeType.PERSIST], mappedBy = "user")
    var detail : UserDetail,

    @OneToOne(cascade = [CascadeType.PERSIST], mappedBy = "user")
    var link : UserLink,
) : Serializable {
    companion object {
        private const val serialVersionUID: Long = 1L
    }
}
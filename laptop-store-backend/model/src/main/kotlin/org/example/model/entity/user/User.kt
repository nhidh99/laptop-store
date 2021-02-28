package org.example.model.entity.user

import org.example.model.entity.token.ConfirmationToken
import org.example.model.type.UserRole
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "\"user\"")
class User(
) : Serializable {
    companion object {
        private const val serialVersionUID: Long = 1L
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    var id: Long? = null

    @Column(name = "username", length = 20, unique = true)
    var username: String? = null

    @Column(name = "password", length = 100)
    var password: String? = null

    @Column(name = "role", length = 10)
    @Enumerated(EnumType.STRING)
    var role: UserRole? = null

    @Column(name = "created_at")
    var createdAt : LocalDateTime? = null

    @OneToOne(cascade = [CascadeType.PERSIST], mappedBy = "user")
    var detail : UserDetail? = null

    @OneToOne(cascade = [CascadeType.PERSIST], mappedBy = "user")
    var link : UserLink? = null

    @OneToMany(mappedBy = "user")
    var tokens : MutableSet<ConfirmationToken>? = null
}
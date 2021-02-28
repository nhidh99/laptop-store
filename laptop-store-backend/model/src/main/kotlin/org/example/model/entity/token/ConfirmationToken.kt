package org.example.model.entity.token

import org.example.constant.TokenConstants
import org.example.model.entity.user.User
import org.example.model.type.TokenType
import org.springframework.beans.factory.annotation.Value
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "confirmation_token")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "token_type")
abstract class ConfirmationToken {
    @Value("\${org.example.server.hostname:error_hostname}")
    private var hostName: String? = null

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    open var id: Long? = null

    @Column(name = "created_at", nullable = false)
    open var createdAt: LocalDateTime? = null

    @Column(name = "expired_at", nullable = false)
    open var expiredAt: LocalDateTime? = null

    @Column(name = "token", nullable = false, length = 1000)
    open var token: String? = null

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    open var user: User? = null

    @Transient
    open var pathname: String? = null

    @Transient
    var confirmationUrl: String? = null
        private set
        get() {
            return "$hostName/$pathname?token=$token"
        }

    @PrePersist
    open fun prePersist() {
        this.token = UUID.randomUUID().toString()
        this.createdAt = LocalDateTime.now(ZoneOffset.UTC)
        this.expiredAt = this.createdAt!!.plusMinutes(TokenConstants.VERIFY_TOKEN_EXPIRATION_IN_MINUTE)
    }
}
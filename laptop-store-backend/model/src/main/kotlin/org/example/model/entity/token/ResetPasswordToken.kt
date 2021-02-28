package org.example.model.entity.token

import org.example.constant.PathnameConstants
import org.example.constant.TokenConstants
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.PrePersist

@Entity
@DiscriminatorValue(TokenConstants.RESET_PASSWORD)
class ResetPasswordToken : ConfirmationToken() {
    @PrePersist
    override fun prePersist() {
        super.prePersist()
        this.pathname = PathnameConstants.RESET_PASSWORD
    }
}
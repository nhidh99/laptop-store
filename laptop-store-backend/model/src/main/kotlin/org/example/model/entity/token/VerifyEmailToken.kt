package org.example.model.entity.token

import org.example.constant.PathnameConstants
import org.example.constant.TokenConstants
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.PrePersist

@Entity
@DiscriminatorValue(TokenConstants.VERIFY_EMAIL)
class VerifyEmailToken : ConfirmationToken() {
    @PrePersist
    override fun initPathnameAndTokenType() {
        this.pathname = PathnameConstants.VERIFY_EMAIL
    }
}
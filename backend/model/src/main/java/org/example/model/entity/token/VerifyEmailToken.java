package org.example.model.entity.token;

import org.example.constant.PathnameConstants;

public class VerifyEmailToken extends ConfirmationToken {
    @Override
    public void prePersist() {
        super.prePersist();
        this.pathname = PathnameConstants.VERIFY_EMAIL;
    }
}

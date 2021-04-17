package org.example.model.entity.token;

import org.example.constant.PathnameConstants;

public class ResetPasswordToken extends ConfirmationToken {
    @Override
    public void prePersist() {
        super.prePersist();
        this.pathname = PathnameConstants.RESET_PASSWORD;
    }
}

package org.example.model.entity.token;

import lombok.NoArgsConstructor;
import org.example.constant.PathnameConstants;

@NoArgsConstructor
public class ResetPasswordToken extends ConfirmationToken {
    @Override
    public void prePersist() {
        super.prePersist();
        this.pathname = PathnameConstants.RESET_PASSWORD;
    }
}

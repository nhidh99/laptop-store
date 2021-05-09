package org.example.model.projection.user;

import org.example.model.type.UserRole;
import org.springframework.beans.factory.annotation.Value;

public interface UserRoleValue {
    @Value("#{target.role}")
    UserRole getValue();
}

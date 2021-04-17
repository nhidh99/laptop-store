package org.example.model.type;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ADMIN,
    CUSTOMER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}

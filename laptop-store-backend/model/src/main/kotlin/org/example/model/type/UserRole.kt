package org.example.model.type

import org.springframework.security.core.GrantedAuthority

enum class UserRole : GrantedAuthority {
    ADMIN,
    CUSTOMER;

    override fun getAuthority(): String {
        return name
    }
}
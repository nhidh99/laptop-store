package org.example.model.projection.user;

import org.example.model.type.UserRole;

public interface UserCredential {
    String getUsername();
    String getPassword();
    UserRole getRole();
}

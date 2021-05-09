package org.example.model.projection.user;

import org.example.model.type.Gender;

public interface UserResponse {
    String getPendingEmail();
    String getVerifiedEmail();
    String getName();
    String getPhone();
    Gender getGender();
}

package org.example.model.projection.user;

import org.springframework.beans.factory.annotation.Value;

public interface UsernameValue {
    @Value("#{target.username}")
    String getValue();
}

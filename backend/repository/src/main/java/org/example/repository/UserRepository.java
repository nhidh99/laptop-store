package org.example.repository;

import org.example.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByVerifiedEmail(String email);

    boolean existsByUsername(String username);

    <T> T findByUsername(String username, Class<T> type);

    <T> T findByVerifiedEmail(String email, Class<T> type);
}

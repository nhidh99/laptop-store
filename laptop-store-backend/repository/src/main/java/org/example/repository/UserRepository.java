package org.example.repository;

import org.example.model.entity.user.User;
import org.example.model.type.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("SELECT u.role FROM User u WHERE u.username = :username")
    UserRole findRoleByUsername(String username);
}

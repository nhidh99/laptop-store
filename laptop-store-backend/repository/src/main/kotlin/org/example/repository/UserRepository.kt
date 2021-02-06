package org.example.repository

import org.example.model.User
import org.example.type.UserRole
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?

    @Query("SELECT u.role FROM User u WHERE u.username = :username")
    fun findRoleByUsername(username: String): UserRole
}
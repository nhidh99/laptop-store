package org.example.security

import org.example.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import kotlin.Throws
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class AppUserDetails @Autowired constructor(private val userRepository: UserRepository) {
    @Throws(UsernameNotFoundException::class)
    fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("User not found")
        return User.withUsername(username).password(user.password)
            .authorities(user.role).accountExpired(false)
            .accountLocked(false).credentialsExpired(false)
            .disabled(false).build()
    }
}
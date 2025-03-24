package com.example.basics.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


//When a user logs in, Spring Security stores their authentication details in the SecurityContextHolder.

@Configuration
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests { auth ->
            auth.requestMatchers("/api/v1/consumers").hasRole("ADMIN")
            auth.anyRequest().authenticated()
        }
        http.httpBasic(withDefaults())
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val adminUser = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("password"))
            .roles("ADMIN")
            .build()

        return InMemoryUserDetailsManager(adminUser)
    }
}

// username here comes in from your Basic Auth in your request
//@Service
//class CustomUserDetailsService(private val authRepository:AuthRepository) : UserDetailsService {
//    override fun loadUserByUsername(username: String): UserDetails {
//        val authRecord = authRepository.findByName(username)
//            ?: throw UsernameNotFoundException("User not found: $username")
//
//        // Convert user role from DB into Spring Security GrantedAuthority
//        val authorities = listOf(SimpleGrantedAuthority(authRecord.role))
//
//        return User(authRecord.name, authRecord.password, authorities)
//    }
//}
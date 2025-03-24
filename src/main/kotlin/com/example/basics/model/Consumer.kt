package com.example.basics.model

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "consumers")
data class Consumer(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    
    @field:NotBlank(message = "First name is required")
    val firstName: String,
    
    @field:NotBlank(message = "Last name is required")
    val lastName: String,
    
    @field:Email(message = "Invalid email format")
    @field:NotBlank(message = "Email is required")
    @Column(unique = true)
    val email: String,

    val age: Int,
)


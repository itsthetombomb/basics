package com.example.basics.model

import jakarta.persistence.*

@Entity
@Table(name = "movies")
data class MovieData(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    
    val budget: String,
    val movieName: String,
    val overview: String,
)


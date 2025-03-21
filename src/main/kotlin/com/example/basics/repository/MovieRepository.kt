package com.example.basics.repository

import com.example.basics.model.MovieData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository : JpaRepository<MovieData, Long>
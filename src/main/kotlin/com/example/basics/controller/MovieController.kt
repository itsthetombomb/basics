package com.example.basics.controller

import com.example.basics.model.MovieData
import com.example.basics.repository.MovieRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/")
class MovieController(private val movieRepository: MovieRepository){

    @GetMapping("/movies")
    fun getAllMovies(): ResponseEntity<List<MovieData>> {
        val movies = movieRepository.findAll()
        return ResponseEntity.ok(movies)
    }
}
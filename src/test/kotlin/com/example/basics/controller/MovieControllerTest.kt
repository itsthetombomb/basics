package com.example.basics.controller

import com.example.basics.model.MovieData
import com.example.basics.repository.MovieRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@WebMvcTest(MovieController::class)
class MovieControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var movieRepository: MovieRepository

    @Test
    fun `should get movie`() {
        val movieData = MovieData(
            id = 1,
            budget = "3000",
            movieName = "NewMovie",
            overview = "Thriller"
        )

        whenever(movieRepository.findById(1)).thenReturn(Optional.of(movieData))

        mockMvc.perform(get("/api/v1/movies/1"))
            .andExpect(status().isOk)
    }
}
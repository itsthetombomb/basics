package com.example.basics.controller

import com.example.basics.model.Consumer
import com.example.basics.repository.ConsumerRepository
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.Optional
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*


@WebMvcTest(ConsumerController::class)
class ConsumerControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var consumerRepository: ConsumerRepository

    @Test
    fun `should create consumer`() {
        val consumer = Consumer(
            firstName = "John",
            lastName = "Doe",
            email = "john.doe@example.com",
            age = 20,
        )
        val savedConsumer = consumer.copy(id = 1)

        whenever(consumerRepository.save(consumer)).thenReturn(savedConsumer)

        mockMvc.perform(post("/api/v1/consumers")
            .with(user("").authorities(SimpleGrantedAuthority("ADMIN")))
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(consumer)))
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.firstName").value("John"))
            .andExpect(jsonPath("$.lastName").value("Doe"))
            .andExpect(jsonPath("$.email").value("john.doe@example.com"))
    }

    @Test
    fun `should get consumer by id`() {
        val consumer = Consumer(
            id = 1,
            firstName = "John",
            lastName = "Doe",
            email = "john.doe@example.com",
            age = 20,
        )

        whenever(consumerRepository.findById(1)).thenReturn(Optional.of(consumer))

        mockMvc.perform(get("/api/v1/consumers/1"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.firstName").value("John"))
            .andExpect(jsonPath("$.lastName").value("Doe"))
            .andExpect(jsonPath("$.email").value("john.doe@example.com"))
    }
}


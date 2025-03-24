package com.example.basics.controller

import com.example.basics.model.Consumer
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.core.ParameterizedTypeReference
import org.springframework.data.domain.PageImpl
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.web.util.UriComponentsBuilder

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = [
        "spring.datasource.url=jdbc:h2:mem:testdb"
    ]
)
@AutoConfigureMockMvc
class ConsumerTests(@Autowired val client: TestRestTemplate) {
    @Test
    fun `create consumer`() {
        val consumer = Consumer(1, "tommy", "payne", "email@aol.com", 1)
        val entity = client
            .withBasicAuth("admin", "password")
            .postForEntity<Consumer>("/api/v1/consumers", consumer)

        assertEquals(HttpStatus.CREATED, entity.statusCode)
        assertEquals(consumer, entity.body)
    }

    @Test
    fun `get consumer by id`() {
        val id = 1L
        val consumer = Consumer(id, "tommy", "payne", "email@aol.com", 1)
        client
            .withBasicAuth("admin", "password")
            .postForEntity<Consumer>("/api/v1/consumers", consumer)

        val entity = client
            .withBasicAuth("admin", "password")
            .getForEntity<Consumer>("/api/v1/consumers/$id")

        assertEquals(consumer, entity.body)
    }
}


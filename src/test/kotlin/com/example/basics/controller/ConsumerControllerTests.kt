package com.example.basics.controller

import com.example.basics.model.Consumer
import com.example.basics.service.ConsumerService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(ConsumerController::class)
class ConsumerControllerTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var consumerService: ConsumerService

    @Test
    @WithMockUser(roles = ["ADMIN"])
    fun `get all customers paginated`() {
        // Create test data
        val consumers = List(15) { i ->
            Consumer(i.toLong(), "Customer $i", "LastName", "customer$i@example.com", 25)
        }
        
        // Create a page of consumers
        val pageable = PageRequest.of(0, 10, Sort.by("id").ascending())
        val page = PageImpl(consumers, pageable, consumers.size.toLong())

        // Mock the service response
        whenever(consumerService.getAllConsumers(0, 10, "id", "asc")).thenReturn(page)

        // Perform the request and verify the response
        mockMvc.perform(get("/api/v1/consumers")
            .param("pageNo", "0")
            .param("pageSize", "10")
            .param("sortId", "id")
            .param("sortDir", "asc")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.content.length()").value(10))
            .andExpect(jsonPath("$.totalElements").value(15))
            .andExpect(jsonPath("$.totalPages").value(2))
            .andExpect(jsonPath("$.number").value(0))
            .andExpect(jsonPath("$.size").value(10))
            .andExpect(jsonPath("$.content[0].firstName").value("Customer 0"))
            .andExpect(jsonPath("$.content[9].firstName").value("Customer 9"))
    }

    @Test
    @WithMockUser(roles = ["ADMIN"])
    fun `get all customers with different page size`() {
        // Create test data
        val consumers = List(15) { i ->
            Consumer(i.toLong(), "Customer $i", "LastName", "customer$i@example.com", 25)
        }
        
        // Create a page of consumers with different page size
        val pageable = PageRequest.of(0, 5, Sort.by("id").ascending())
        val page = PageImpl(consumers, pageable, consumers.size.toLong())

        // Mock the service response
        whenever(consumerService.getAllConsumers(0, 5, "id", "asc")).thenReturn(page)

        // Perform the request and verify the response
        mockMvc.perform(get("/api/v1/consumers")
            .param("pageNo", "0")
            .param("pageSize", "5")
            .param("sortId", "id")
            .param("sortDir", "asc")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.content.length()").value(5))
            .andExpect(jsonPath("$.totalElements").value(15))
            .andExpect(jsonPath("$.totalPages").value(3))
            .andExpect(jsonPath("$.number").value(0))
            .andExpect(jsonPath("$.size").value(5))
    }
}
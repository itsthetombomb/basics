package com.example.basics.controller

import com.example.basics.model.Consumer
import com.example.basics.service.ConsumerService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/consumers")
class ConsumerController(private val consumerService: ConsumerService) {

    // @Valid Automatically checks that the request body meets validation criteria before processing the method.
    @PostMapping
    fun createConsumer(@Valid @RequestBody consumer: Consumer): ResponseEntity<Consumer> {
        val savedConsumer = consumerService.addConsumer(consumer)
        return ResponseEntity(savedConsumer, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getConsumer(@PathVariable id: Long): ResponseEntity<Consumer> {
        val consumer = consumerService.getConsumer(id)
        return consumer?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @GetMapping
    fun getAllConsumers(
        @RequestParam(defaultValue = "0") pageNo: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        @RequestParam(defaultValue = "age") sortId: String,
        @RequestParam(defaultValue = "dir") sortDir: String,
    ): ResponseEntity<Page<Consumer>> {
        val consumers = consumerService.getAllConsumers(pageNo, pageSize, sortId, sortDir)
        return ResponseEntity.ok(consumers)
    }
}


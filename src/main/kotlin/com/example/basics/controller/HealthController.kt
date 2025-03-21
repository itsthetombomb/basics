package com.example.basics.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/")
class HealthController {
    @GetMapping("/health")
    fun getHealth() : ResponseEntity<String> {
        return ResponseEntity.ok("OK")
    }
}
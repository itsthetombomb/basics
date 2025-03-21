package com.example.basics.repository

import com.example.basics.model.Consumer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ConsumerRepository : JpaRepository<Consumer, Long> {
    // Called Query Derivation
    fun findByFirstName(firstName: String) : Consumer?

    // Note, we reference the Consumer object, not the consumers table
    @Query("""
        SELECT c from Consumer c WHERE c.firstName = :firstName AND c.lastName = :lastName
    """)
    fun getByFirstAndLastName(@Param("firstName") firstName: String, @Param("lastName") lastName: String) : Consumer?
}


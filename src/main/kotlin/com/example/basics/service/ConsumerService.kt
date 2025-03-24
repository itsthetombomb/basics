package com.example.basics.service

import com.example.basics.model.Consumer
import com.example.basics.repository.ConsumerRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ConsumerService(private val consumerRepository: ConsumerRepository){

    fun addConsumer(consumer: Consumer): Consumer {
        return consumerRepository.save(consumer)
    }

    fun getConsumer(id: Long): Consumer? {
        return consumerRepository.findByIdOrNull(id)
    }

    fun getAllConsumers(pageNo: Int, pageSize: Int, sortId: String, sortDir: String): Page<Consumer> {
        val sort = if(sortDir == "asc") {
            Sort.by(sortId).ascending()
        } else {
            Sort.by(sortId).descending()
        }
        val pageable = PageRequest.of(pageNo, pageSize, sort)
        return consumerRepository.findAll(pageable)
    }
}


// Cursor vs Offset Based Pagination

// Offset Based:
// Jump to any page directly, which is useful for ui pannels
// Poor preformance on large data, database must skip over potentially huge number of records
// If the underlying data changes between requests, the results might be inconsistent

// Cursor Based:
// use a pointer to remember the last item, better consistency
// Scales better and reduces data inconstancy when items are added or removed


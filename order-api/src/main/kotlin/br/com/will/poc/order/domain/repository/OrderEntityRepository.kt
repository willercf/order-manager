package br.com.will.poc.order.domain.repository

import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface OrderEntityRepository : MongoRepository<OrderEntity, UUID> {

    fun findByExternalId(externalId: UUID): OrderEntity?
}
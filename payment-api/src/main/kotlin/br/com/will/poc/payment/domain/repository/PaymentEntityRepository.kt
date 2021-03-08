package br.com.will.poc.payment.domain.repository

import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface PaymentEntityRepository : MongoRepository<PaymentEntity, UUID> {

    fun findByExternalId(externalId: UUID): PaymentEntity?
}
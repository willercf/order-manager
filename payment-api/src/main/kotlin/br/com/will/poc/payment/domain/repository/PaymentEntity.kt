package br.com.will.poc.payment.domain.repository

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.Instant
import java.util.*

enum class PaymentStatus {
    PENDING,
    DENIED,
    APPROVED
}

@Document(value = "payment")
class PaymentEntity(
    @Id
    val id: ObjectId = ObjectId.get(),
    val externalId: UUID,
    var cardNumber: String,
    var expiration: String,
    var cvv: String,
    var value: BigDecimal,
    @Indexed
    var status: PaymentStatus = PaymentStatus.PENDING,
    val createdAt: Instant,
    var updatedAt: Instant
)

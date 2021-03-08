package br.com.will.poc.payment.domain.listener

import br.com.will.poc.order.domain.event.CreateOrderEvent
import br.com.will.poc.order.domain.event.UpdateOrderEvent
import br.com.will.poc.payment.domain.repository.PaymentEntity
import br.com.will.poc.payment.domain.repository.PaymentEntityRepository
import org.axonframework.eventhandling.EventHandler
import org.bson.types.ObjectId
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class PaymentListener {

    val LOGGER: Logger = LoggerFactory.getLogger(PaymentListener::class.java)
    var repository: PaymentEntityRepository

    @Autowired
    constructor(repository: PaymentEntityRepository) {
        this.repository = repository
    }

    @EventHandler
    fun on(event: CreateOrderEvent) {

        LOGGER.info("===========================")
        LOGGER.info("= CREATE PAYMENT LISTENER")
        LOGGER.info("===========================")
        LOGGER.info("id: ${event.id}")
        LOGGER.info("created at: ${event.createdAt}")
        LOGGER.info("===========================")

        val entity = PaymentEntity(ObjectId.get(),
            event.id,
            event.payment.cardNumber,
            event.payment.expiration,
            event.payment.cvv,
            event.payment.value,
            createdAt = Instant.now(),
            updatedAt = Instant.now()
        )
        repository.save(entity)
        LOGGER.info("end...")
        LOGGER.info("===========================")
    }

    @EventHandler
    fun on(event: UpdateOrderEvent) {

        LOGGER.info("===========================")
        LOGGER.info("= UPDATE PAYMENT LISTENER")
        LOGGER.info("===========================")
        LOGGER.info("id: ${event.id}")
        LOGGER.info("Status: ${event.status}")
        LOGGER.info("===========================")

        val entity = repository.findByExternalId(event.id)
        if (entity != null) {
            entity.apply {
                cardNumber = event.payment.cardNumber
                expiration = event.payment.expiration
                cvv = event.payment.cvv
                value = event.payment.value
                updatedAt = Instant.now()
            }
            repository.save(entity)
        } else {
            LOGGER.warn("Not found, discarted...")
        }
        LOGGER.info("end...")
        LOGGER.info("===========================")
    }
}

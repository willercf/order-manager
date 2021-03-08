package br.com.will.poc.order.domain.listener

import br.com.will.poc.order.domain.event.CreateOrderEvent
import br.com.will.poc.order.domain.event.UpdateOrderEvent
import br.com.will.poc.order.domain.repository.OrderEntity
import br.com.will.poc.order.domain.repository.OrderEntityRepository
import org.axonframework.eventhandling.EventHandler
import org.bson.types.ObjectId
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class OrderListener {

    private val LOGGER = LoggerFactory.getLogger(OrderListener::class.java)
    var repository: OrderEntityRepository

    @Autowired
    constructor(repository: OrderEntityRepository) {
        this.repository = repository
    }

    @EventHandler
    fun on(event: CreateOrderEvent) {

        LOGGER.info("===========================")
        LOGGER.info("= ORDER LISTENER")
        LOGGER.info("===========================")
        LOGGER.info("id: ${event.id}")
        LOGGER.info("name: ${event.createdAt}")
        LOGGER.info("===========================")

        val entity = OrderEntity(ObjectId.get(),
            event.id,
            event.shoppingCart,
            event.address,
            event.payment,
            event.status,
            event.createdAt ?: Instant.now(),
            event.updatedAt ?: Instant.now()
        )
        repository.save(entity)
        LOGGER.info("end...")
        LOGGER.info("===========================")
    }

    @EventHandler
    fun on(event: UpdateOrderEvent) {

        LOGGER.info("===========================")
        LOGGER.info("= ORDER LISTENER")
        LOGGER.info("===========================")
        LOGGER.info("id: ${event.id}")
        LOGGER.info("name: ${event.updatedAt}")
        LOGGER.info("===========================")

        val entity = repository.findByExternalId(event.id)
        if (entity != null) {
            entity.apply {
                shoppingCart = event.shoppingCart
                address = event.address
                payment = event.payment
                status = event.status
                updatedAt = event.updatedAt
            }
            repository.save(entity)
        } else {
            LOGGER.info("Not found...")
        }
        LOGGER.info("end...")
        LOGGER.info("===========================")
    }
}

package br.com.will.poc.order.domain.aggregate

import br.com.will.poc.order.domain.command.CreateOrderCommand
import br.com.will.poc.order.domain.command.UpdateOrderCommand
import br.com.will.poc.order.domain.event.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.spring.stereotype.Aggregate
import java.time.Instant
import java.util.*

@Aggregate
class Order {

    @AggregateIdentifier
    lateinit var id: UUID
    lateinit var shoppingCart: MutableList<ShoppingCartItem>
    lateinit var address: Address
    lateinit var payment: Payment
    lateinit var status: OrderStatus
    lateinit var createdAt: Instant
    lateinit var updatedAt: Instant

    constructor()

    @CommandHandler
    constructor(cmd: CreateOrderCommand) {
        apply(
            CreateOrderEvent(
                cmd.id, cmd.shoppingCart, cmd.address, cmd.payment, createdAt = Instant.now(), updatedAt = Instant.now()
            )
        )
    }

    @CommandHandler
    fun updateCmd(cmd: UpdateOrderCommand) {
        apply(
            UpdateOrderEvent(
                cmd.id, cmd.shoppingCart, cmd.address, cmd.payment, cmd.status, Instant.now()
            )
        )
    }

    @EventSourcingHandler
    private fun create(event: CreateOrderEvent) {
        id = event.id
        shoppingCart = event.shoppingCart
        address = event.address
        payment = event.payment
        status = event.status
        createdAt = event.createdAt
        updatedAt = event.updatedAt
    }

    @EventSourcingHandler
    private fun updateEvt(event: UpdateOrderEvent) {
        if (status != OrderStatus.RECEIVED) {
            throw Exception()
        }
        id = event.id
        shoppingCart = event.shoppingCart
        address = event.address
        payment = event.payment
        status = event.status
        updatedAt = event.updatedAt
    }
}
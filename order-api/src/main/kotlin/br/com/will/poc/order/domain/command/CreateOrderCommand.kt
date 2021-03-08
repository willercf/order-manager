package br.com.will.poc.order.domain.command

import br.com.will.poc.order.domain.event.Address
import br.com.will.poc.order.domain.event.Payment
import br.com.will.poc.order.domain.event.ShoppingCartItem
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class CreateOrderCommand(
    @TargetAggregateIdentifier
    val id: UUID,
    val shoppingCart: MutableList<ShoppingCartItem>,
    val address: Address,
    val payment: Payment
)

package br.com.will.poc.order.domain.controller

import br.com.will.poc.order.domain.event.Address
import br.com.will.poc.order.domain.event.OrderStatus
import br.com.will.poc.order.domain.event.Payment
import br.com.will.poc.order.domain.event.ShoppingCartItem
import java.util.*

data class UpdateOrderRequest(
    val id: UUID?,
    val shoppingCart: MutableList<ShoppingCartItem>,
    val address: Address,
    val payment: Payment,
    val status: OrderStatus
)
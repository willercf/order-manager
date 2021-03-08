package br.com.will.poc.order.domain.controller

import br.com.will.poc.order.domain.event.Address
import br.com.will.poc.order.domain.event.Payment
import br.com.will.poc.order.domain.event.ShoppingCartItem

data class CreateOrderRequest(
    val shoppingCart: MutableList<ShoppingCartItem>,
    val address: Address,
    val payment: Payment
)

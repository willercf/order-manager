package br.com.will.poc.order.domain.event

import java.math.BigDecimal
import java.time.Instant
import java.util.*

data class Product(
    val id: Long,
    val name: String,
    val price: BigDecimal
)

data class ShoppingCartItem(
    val product: Product,
    val quantity: Int
)

data class Address(
    val zipCode: String,
    val street: String,
    val number: String,
    val neighborhood: String,
    val city: String,
    val uf: String
)

data class Payment(
    val cardNumber: String,
    val expiration: String,
    val cvv: String,
    val value: BigDecimal
)

enum class OrderStatus {
    RECEIVED,
    CANCELLED,
    CONFIRMED
}

class CreateOrderEvent(
    val id: UUID,
    val shoppingCart: MutableList<ShoppingCartItem>,
    val address: Address,
    val payment: Payment,
    val status: OrderStatus = OrderStatus.RECEIVED,
    val createdAt: Instant,
    val updatedAt: Instant
)

class UpdateOrderEvent(
    val id: UUID,
    val shoppingCart: MutableList<ShoppingCartItem>,
    val address: Address,
    val payment: Payment,
    val status: OrderStatus,
    val updatedAt: Instant
)

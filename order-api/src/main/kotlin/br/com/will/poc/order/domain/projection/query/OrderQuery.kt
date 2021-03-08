package br.com.will.poc.order.domain.projection.query

import br.com.will.poc.order.domain.event.Address
import br.com.will.poc.order.domain.event.OrderStatus
import br.com.will.poc.order.domain.event.Payment
import br.com.will.poc.order.domain.event.ShoppingCartItem
import org.bson.types.ObjectId
import java.time.Instant
import java.util.*

data class OrderQuery(
    val id: ObjectId,
    val externalId: UUID,
    var shoppingCart: MutableList<ShoppingCartItem>,
    var address: Address,
    var payment: Payment,
    var status: OrderStatus,
    val createdAt: Instant,
    var updatedAt: Instant
)
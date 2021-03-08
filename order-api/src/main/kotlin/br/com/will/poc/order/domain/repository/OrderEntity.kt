package br.com.will.poc.order.domain.repository

import br.com.will.poc.order.domain.event.Address
import br.com.will.poc.order.domain.event.OrderStatus
import br.com.will.poc.order.domain.event.Payment
import br.com.will.poc.order.domain.event.ShoppingCartItem
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.*

@Document(value = "order")
class OrderEntity(
    @Id
    val id: ObjectId = ObjectId.get(),
    val externalId: UUID,
    var shoppingCart: MutableList<ShoppingCartItem>,
    var address: Address,
    var payment: Payment,
    @Indexed
    var status: OrderStatus,
    @Indexed
    val createdAt: Instant,
    @Indexed
    var updatedAt: Instant
)
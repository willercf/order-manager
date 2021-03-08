package br.com.will.poc.order.domain.projection

import br.com.will.poc.order.domain.projection.query.OrderQuery
import br.com.will.poc.order.domain.repository.OrderEntity
import br.com.will.poc.order.domain.repository.OrderEntityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors

@Component
class OrderProjector {

    var repository: OrderEntityRepository

    @Autowired
    constructor(repository: OrderEntityRepository) {
        this.repository = repository
    }

    fun findAll(): List<OrderQuery> {
        return repository.findAll().stream()
            .map { toQuery(it) }
            .collect(Collectors.toList())
    }

    fun findByExternalId(externalId: UUID): OrderQuery? {
        return repository.findByExternalId(externalId).let { toQuery(it!!) }
    }

    private fun toQuery(it: OrderEntity): OrderQuery {
        return OrderQuery(it.id,
            it.externalId,
            it.shoppingCart,
            it.address,
            it.payment,
            it.status,
            it.createdAt,
            it.updatedAt)
    }
}
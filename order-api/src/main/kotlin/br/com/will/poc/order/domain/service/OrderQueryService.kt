package br.com.will.poc.order.domain.service

import br.com.will.poc.order.domain.projection.OrderProjector
import br.com.will.poc.order.domain.projection.query.OrderQuery
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderQueryService {

    var projection: OrderProjector

    @Autowired
    constructor(projection: OrderProjector) {
        this.projection = projection
    }

    fun findAll(): List<OrderQuery> = projection.findAll()

    fun findByExternalId(externalId: UUID) = projection.findByExternalId(externalId)
}

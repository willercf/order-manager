package br.com.will.poc.order.domain.controller

import br.com.will.poc.order.domain.service.OrderCommandService
import br.com.will.poc.order.domain.service.OrderQueryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(value = ["/v1/orders"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
class OrderController {

    var commandService: OrderCommandService
    var queryService: OrderQueryService

    @Autowired
    constructor(commandService: OrderCommandService, queryService: OrderQueryService) {
        this.commandService = commandService
        this.queryService = queryService
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun checkout(@RequestBody request: CreateOrderRequest) = commandService.checkout(request)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun update(@PathVariable id: UUID,
               @RequestBody request: UpdateOrderRequest) =
        commandService.update(UpdateOrderRequest(id,
            request.shoppingCart,
            request.address,
            request.payment,
            request.status))

    @GetMapping
    fun findAll() = queryService.findAll()

    @GetMapping("/{externalId}")
    fun findByExternalId(@PathVariable externalId: UUID) = queryService.findByExternalId(externalId)
}

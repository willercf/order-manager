package br.com.will.poc.order.domain.service

import br.com.will.poc.order.domain.command.CreateOrderCommand
import br.com.will.poc.order.domain.command.UpdateOrderCommand
import br.com.will.poc.order.domain.controller.CreateOrderRequest
import br.com.will.poc.order.domain.controller.UpdateOrderRequest
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderCommandService (var commandGateway: CommandGateway) {

    fun checkout(request: CreateOrderRequest) {
        if (validateCreate(request)) {
            commandGateway.sendAndWait<CreateOrderCommand>(
                CreateOrderCommand(UUID.randomUUID(),
                    request.shoppingCart,
                    request.address,
                    request.payment
                )
            )
        }
    }

    fun update(request: UpdateOrderRequest) {
        if (validateUpdate(request)) {
            commandGateway.sendAndWait<UpdateOrderCommand>(
                UpdateOrderCommand(request.id!!,
                    request.shoppingCart,
                    request.address,
                    request.payment,
                    request.status
                )
            )
        }
    }

    private fun validateCreate(request: CreateOrderRequest): Boolean {
        return true;
    }

    private fun validateUpdate(request: UpdateOrderRequest): Boolean {
        return true;
    }
}

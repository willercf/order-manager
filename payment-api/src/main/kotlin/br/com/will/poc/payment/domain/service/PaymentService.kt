package br.com.will.poc.payment.domain.service

import br.com.will.poc.payment.domain.controller.ApprovePaymentRequest
import br.com.will.poc.payment.domain.repository.PaymentEntityRepository
import org.axonframework.commandhandling.gateway.CommandGateway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class PaymentService {

    val LOGGER: Logger = LoggerFactory.getLogger(PaymentService::class.java)
    var commandGateway: CommandGateway
    var repository: PaymentEntityRepository

    @Autowired
    constructor(commandGateway: CommandGateway, repository: PaymentEntityRepository) {
        this.commandGateway = commandGateway
        this.repository = repository
    }


    fun approve(request: ApprovePaymentRequest) {
        /*
        commandGateway.sendAndWait<ApprovePaymentCommand>(
                ApprovePaymentCommand(request.id, request.status)
        )
         */
        LOGGER.info("===========================")
        LOGGER.info("= APPROVE PAYMENT LISTENER")
        LOGGER.info("===========================")
        LOGGER.info("id: ${request.id}")
        LOGGER.info("Status: ${request.status}")
        LOGGER.info("===========================")

        val entity = repository.findByExternalId(request.id)
        if (entity != null) {
            entity.status = request.status
            entity.updatedAt = Instant.now()
            repository.save(entity)
        } else {
            LOGGER.warn("Not found, discarted...")
        }
        LOGGER.info("end...")
        LOGGER.info("===========================")
    }
}


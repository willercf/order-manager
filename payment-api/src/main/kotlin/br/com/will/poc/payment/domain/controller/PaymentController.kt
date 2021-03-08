package br.com.will.poc.payment.domain.controller

import br.com.will.poc.payment.domain.service.PaymentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/v1/payments"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
class PaymentController {

    var service: PaymentService

    @Autowired
    constructor(service: PaymentService) {
        this.service = service
    }

    @PutMapping
    fun approve(@RequestBody request: ApprovePaymentRequest) = service.approve(request)
}

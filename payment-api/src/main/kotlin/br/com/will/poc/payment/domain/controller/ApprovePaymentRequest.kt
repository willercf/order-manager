package br.com.will.poc.payment.domain.controller

import br.com.will.poc.payment.domain.repository.PaymentStatus
import java.util.*

data class ApprovePaymentRequest(
    val id: UUID,
    val status: PaymentStatus
)

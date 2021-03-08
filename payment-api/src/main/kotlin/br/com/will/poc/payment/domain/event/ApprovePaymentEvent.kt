package br.com.will.poc.payment.domain.event

import br.com.will.poc.payment.domain.repository.PaymentStatus
import java.util.*

class ApprovePaymentEvent(
    val id: UUID,
    val status: PaymentStatus
)

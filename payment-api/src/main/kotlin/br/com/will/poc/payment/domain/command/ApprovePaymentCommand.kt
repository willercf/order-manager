package br.com.will.poc.payment.domain.command

import br.com.will.poc.payment.domain.repository.PaymentStatus
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class ApprovePaymentCommand (
    @TargetAggregateIdentifier
    val id: UUID,
    val status: PaymentStatus
)

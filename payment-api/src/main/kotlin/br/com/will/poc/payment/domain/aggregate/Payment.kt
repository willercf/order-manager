package br.com.will.poc.payment.domain.aggregate

import br.com.will.poc.payment.domain.command.ApprovePaymentCommand
import br.com.will.poc.payment.domain.event.ApprovePaymentEvent
import br.com.will.poc.payment.domain.repository.PaymentStatus
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.spring.stereotype.Aggregate
import java.time.Instant
import java.util.*

@Aggregate
class Payment {

    @AggregateIdentifier
    lateinit var id: UUID
    lateinit var status: PaymentStatus
    lateinit var changedAt: Instant

    constructor()

    @CommandHandler
    fun approve(command: ApprovePaymentCommand) {
        apply(ApprovePaymentEvent(command.id, command.status))
    }

    @EventSourcingHandler
    private fun approve(event: ApprovePaymentEvent) {
        id = event.id
        status = event.status
        changedAt = Instant.now()
    }
}

package co.com.eventdriven.designpatterns.gateways.services;

import co.com.eventdriven.designpatterns.model.base.PaymentTransactionBase;
import co.com.eventdriven.designpatterns.model.payments.PaymentTransactionInfo;
import reactor.core.publisher.Mono;

public interface TransactionStrategy<T extends PaymentTransactionBase> {

    Boolean isValid(PaymentTransactionBase event);
    Mono<PaymentTransactionInfo> process(T event);
}

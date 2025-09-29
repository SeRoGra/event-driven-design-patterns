package co.com.eventdriven.designpatterns.usecase.transaction;

import co.com.eventdriven.designpatterns.gateways.services.TransactionStrategy;
import co.com.eventdriven.designpatterns.model.base.PaymentTransactionBase;
import co.com.eventdriven.designpatterns.model.payments.PaymentTransactionInfo;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class PendingTransactionUseCase {

    private final Logger logger = Logger.getLogger(PendingTransactionUseCase.class.getName());
    private final List<TransactionStrategy<? extends PaymentTransactionBase>> strategies;

    public <T extends PaymentTransactionBase> Mono<Void> process(T event) {

        return Mono.just(event)
                .doOnNext(inEvent -> logger.info(String.format("Origen de Evento %s", inEvent.getOrigin())))
                .flatMap(this::executeStrategy)
                .doOnTerminate(() -> logger.info("FIN Procesamiento"))
                .then();

    }

    private <T extends PaymentTransactionBase> Mono<PaymentTransactionInfo> executeStrategy(T event) {
        return Flux.fromIterable(strategies)
                .filter(strategy -> strategy.isValid(event))
                .next()
                .cast((Class<TransactionStrategy<T>>) (Class<?>) TransactionStrategy.class)
                .flatMap(strategy -> strategy.process(event))
                .doOnSuccess(paymentTransactionInfo ->
                        logger.info(String.format("RESPONSE PaymentTransactionInfo :: %s", paymentTransactionInfo)));
    }

}
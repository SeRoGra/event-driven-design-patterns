package co.com.eventdriven.designpatterns.sqs.listener.factory;

import co.com.eventdriven.designpatterns.enums.TransactionInfoType;
import co.com.eventdriven.designpatterns.model.base.PaymentTransactionBase;
import co.com.eventdriven.designpatterns.sqs.listener.mapper.EventMapper;
import co.com.eventdriven.designpatterns.usecase.transaction.PendingTransactionUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionStrategyFactory {

    private final List<EventMapper<? extends PaymentTransactionBase>> strategies;
    private final PendingTransactionUseCase pendingTransactionUseCase;

    public Mono<Void> dispatch(String payload) {

        return Flux.fromIterable(strategies)
                .filter(eventMapper -> eventMapper.validate(payload))
                .switchIfEmpty(Mono.error(TransactionInfoType.BAD_REQUEST.build("The event does not match any strategy")))
                .next()
                .map(eventMapper -> eventMapper.mapper(payload))
                .flatMap(pendingTransactionUseCase::process);
    }

}

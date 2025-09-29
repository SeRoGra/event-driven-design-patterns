package co.com.eventdriven.designpatterns.sqs.listener;

import co.com.eventdriven.designpatterns.model.base.PaymentTransactionBase;
import co.com.eventdriven.designpatterns.sqs.listener.enums.TransactionLabels;
import co.com.eventdriven.designpatterns.sqs.listener.factory.TransactionStrategyFactory;
import co.com.eventdriven.designpatterns.sqs.listener.mapper.JsonHelperService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;
import software.amazon.awssdk.services.sqs.model.Message;

import java.util.Optional;
import java.util.function.Function;

import static co.com.eventdriven.designpatterns.sqs.listener.constants.OperationMessagesSqs.*;
import static co.com.eventdriven.designpatterns.sqs.listener.enums.TransactionLabels.DETAIL;
import static net.logstash.logback.argument.StructuredArguments.kv;

import brave.Tracer;

@Slf4j
@Service
@RequiredArgsConstructor
public class SQSPaymentFilteringProcessor implements Function<Message, Mono<Void>> {

    private final TransactionStrategyFactory transactionStrategyFactory;
    private final JsonHelperService jsonHelperService;
    private final Tracer tracer;

    @Override
    public Mono<Void> apply(Message message) {

        var span = tracer.nextSpan().name(TRACER_NAME).start();
        var payload = extractPayload(message.body());
        var transactionBase = mapperPayload(payload);

        return Mono.just(payload)
                .flatMap(transactionStrategyFactory::dispatch)
                .contextCapture()
                .contextWrite(Context.of(
                        MESSAGE_ID, transactionBase.getMessageId(),
                        DNI_ID, transactionBase.getDni(),
                        SPAN_ID, span.context().spanIdString()
                ))
                .doOnSubscribe(event -> log.info(REQUEST_PAYMENT_FILTERING_TRANSACTION,
                        kv(TransactionLabels.TRANSACTION.name(), message.body()), transactionBase.getMessageId(),
                        transactionBase.getDni(), span.context().spanIdString()))
                .doOnError(e -> log.info(ERROR_PROCESS_FILTERING, span.context().spanIdString(), e.toString(), e))
                .onErrorResume(Exception.class, e -> Mono.empty());
    }

    private String extractPayload(String payload) {
        JsonNode event = jsonHelperService.jsonStringToJsonNode(payload);
        event = Optional.ofNullable(event.get(DETAIL.getKey())).orElse(event);
        return event.toString();
    }

    private PaymentTransactionBase mapperPayload(String payload) {
        JsonNode event = jsonHelperService.jsonStringToJsonNode(payload);
        return jsonHelperService.jsonNodeToObject(event, PaymentTransactionBase.class);
    }

}

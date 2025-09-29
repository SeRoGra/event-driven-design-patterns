package co.com.eventdriven.designpatterns.usecase.strategies;

import co.com.eventdriven.designpatterns.gateways.services.TransactionStrategy;
import co.com.eventdriven.designpatterns.model.base.PaymentTransactionBase;
import co.com.eventdriven.designpatterns.model.payments.PaymentTransactionInfo;
import co.com.eventdriven.designpatterns.model.payments.card.CardPaymentTransaction;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

import static co.com.eventdriven.designpatterns.enums.PaymentMethodType.PHYSICAL_CARD;

public class CardTransactionStrategy implements TransactionStrategy<CardPaymentTransaction> {

    private final Logger logger = Logger.getLogger(CardTransactionStrategy.class.getName());

    @Override
    public Boolean isValid(PaymentTransactionBase event) {
        return PHYSICAL_CARD.name().equals(event.getTypeTransaction());
    }

    @Override
    public Mono<PaymentTransactionInfo> process(CardPaymentTransaction event) {
        return Mono.just(event)
                .doOnNext(inEvent -> logger.info("INICIO Strategy CardTransactionStrategy"))
                .doOnNext(inEvent -> logger.info("Validando Score de Fraude"))
                .doOnNext(inEvent -> logger.info("Verificando datos de la tarjeta"))
                .doOnNext(inEvent -> logger.info("Procesando autenticación 3DS2"))
                .doOnNext(inEvent -> logger.info("Validando comercio y MCC"))
                .doOnNext(inEvent -> logger.info("Calculando importe total y cuotas"))
                .doOnNext(inEvent -> logger.info("Registrando ítems comprados"))
                .doOnNext(inEvent -> logger.info("Registrando canal y versión de la app"))
                .doOnNext(inEvent -> logger.info("FIN Strategy CardTransactionStrategy"))
                .map(this::buildPaymentInfo);
    }

    private PaymentTransactionInfo buildPaymentInfo(CardPaymentTransaction event) {
        return PaymentTransactionInfo.builder()
                .referenceId(event.getMessageId())
                .dni(event.getDni())
                .commerce(event.getMerchant().getName())
                .amount(event.getPayment().getAmount().getTotal())
                .paymentMethod(event.getTypeTransaction())
                .purchaseDate(event.getCreatedAt())
                .build();
    }

}

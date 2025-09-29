package co.com.eventdriven.designpatterns.usecase.strategies;

import co.com.eventdriven.designpatterns.gateways.services.TransactionStrategy;
import co.com.eventdriven.designpatterns.model.base.PaymentTransactionBase;
import co.com.eventdriven.designpatterns.model.payments.PaymentTransactionInfo;
import co.com.eventdriven.designpatterns.model.payments.paypal.PaypalPaymentTransaction;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

import static co.com.eventdriven.designpatterns.enums.PaymentMethodType.PAYPAL;

public class PaypalTransactionStrategy implements TransactionStrategy<PaypalPaymentTransaction> {

    private final Logger logger = Logger.getLogger(PaypalTransactionStrategy.class.getName());

    @Override
    public Boolean isValid(PaymentTransactionBase event) {
        return PAYPAL.name().equals(event.getOrigin().split("_")[0]);
    }

    @Override
    public Mono<PaymentTransactionInfo> process(PaypalPaymentTransaction event) {
        return Mono.just(event)
                .doOnNext(inEvent -> logger.info("INICIO Strategy PaypalTransactionStrategy"))
                .doOnNext(inEvent -> logger.info("Validando autenticidad del Webhook PayPal"))
                .doOnNext(inEvent -> logger.info("Procesando capturas de pago y conversión de moneda"))
                .doOnNext(inEvent -> logger.info("Verificando información del pagador y fuente de fondos"))
                .doOnNext(inEvent -> logger.info("Validando seller protection y captureId"))
                .doOnNext(inEvent -> logger.info("Registrando ítems comprados y dirección de envío"))
                .doOnNext(inEvent -> logger.info("Registrando canal de ingreso y metadatos del webhook"))
                .doOnNext(inEvent -> logger.info("FIN Strategy PaypalTransactionStrategy"))
                .map(this::buildPaymentInfo);
    }

    private PaymentTransactionInfo buildPaymentInfo(PaypalPaymentTransaction event) {
        return PaymentTransactionInfo.builder()
                .referenceId(event.getMessageId())
                .dni(event.getDni())
                .commerce(getCommerce(event.getEvent().getEventId()))
                .amount(event.getEvent().getResource().getExchange().getConvertedValue())
                .paymentMethod(event.getReference1().toUpperCase())
                .purchaseDate(event.getEvent().getCreateTime())
                .build();
    }

    private String getCommerce(String eventId) {
        // Se consume un api externa para retornar el valor del comercio donde se realizó la compra
        return "UDEMY";
    }

}

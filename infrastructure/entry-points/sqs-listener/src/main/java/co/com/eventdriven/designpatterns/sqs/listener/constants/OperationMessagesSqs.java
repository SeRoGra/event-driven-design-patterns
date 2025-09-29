package co.com.eventdriven.designpatterns.sqs.listener.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class OperationMessagesSqs {

    public static final String REQUEST_PAYMENT_FILTERING_TRANSACTION = "Request Payment Transaction :: {}, " +
            "messageId: {} , dni: {}, spanId:{}";

    public static final String SPAN_ID = "spanId";
    public static final String DNI_ID = "dni";
    public static final String MESSAGE_ID = "messageId";
    public static final String TRACER_NAME = "payment-event-span-id";

    public static final String ERROR_PROCESS_FILTERING = "Error Filtering Processor :: spanId:{}, {}";

}

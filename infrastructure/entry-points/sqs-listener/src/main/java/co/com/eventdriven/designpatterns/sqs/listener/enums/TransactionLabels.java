package co.com.eventdriven.designpatterns.sqs.listener.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionLabels {

    TRANSACTION("type","TRANSACTION"),
    SUBTYPE("subtype","PAYMENT"),
    DETAIL("detail", ""),
    PAYMENT_TYPE("reference1", ""),

    PHYSICAL_CARD("typeTransaction","PHYSICAL_CARD"),
    PAYPAL(PAYMENT_TYPE.key, "PayPal"),
    PSE(PAYMENT_TYPE.key,"PSE");

    private final String key;
    private final String value;

}

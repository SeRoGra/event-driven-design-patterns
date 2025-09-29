package co.com.eventdriven.designpatterns.model.payments.paypal;

import co.com.eventdriven.designpatterns.model.base.PaymentTransactionBase;
import co.com.eventdriven.designpatterns.model.payments.paypal.dto.PaypalEvent;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class PaypalPaymentTransaction extends PaymentTransactionBase {

    private String reference1;
    private String reference2;
    private String reference3;
    private String reference4;
    private String paypalWebhookId;
    private PaypalEvent event;

}

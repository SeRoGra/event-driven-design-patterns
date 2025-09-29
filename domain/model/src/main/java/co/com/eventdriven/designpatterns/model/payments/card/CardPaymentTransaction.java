package co.com.eventdriven.designpatterns.model.payments.card;

import co.com.eventdriven.designpatterns.model.base.PaymentTransactionBase;
import co.com.eventdriven.designpatterns.model.payments.card.dto.CardMerchant;
import co.com.eventdriven.designpatterns.model.payments.card.dto.CardPayment;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class CardPaymentTransaction extends PaymentTransactionBase {

    private CardPayment payment;
    private CardMerchant merchant;
    private String createdAt;

}

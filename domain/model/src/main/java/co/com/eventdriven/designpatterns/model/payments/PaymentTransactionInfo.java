package co.com.eventdriven.designpatterns.model.payments;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PaymentTransactionInfo {

    private String referenceId;
    private String commerce;
    private String amount;
    private String paymentMethod;
    private String purchaseDate;
    private String dni;

}

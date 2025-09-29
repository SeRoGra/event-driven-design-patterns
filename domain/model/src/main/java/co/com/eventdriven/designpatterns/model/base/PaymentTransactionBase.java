package co.com.eventdriven.designpatterns.model.base;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentTransactionBase {

    private String dni;
    private String origin;
    private String typeTransaction;
    private String messageId;

}

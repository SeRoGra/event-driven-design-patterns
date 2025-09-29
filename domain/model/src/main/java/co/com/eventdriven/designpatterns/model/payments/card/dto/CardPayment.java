package co.com.eventdriven.designpatterns.model.payments.card.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardPayment {

    private String method;
    private CardAmount amount;

}

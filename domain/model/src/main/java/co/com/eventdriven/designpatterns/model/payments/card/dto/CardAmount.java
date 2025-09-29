package co.com.eventdriven.designpatterns.model.payments.card.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardAmount {

    private String currency;
    private String total;

}

package co.com.eventdriven.designpatterns.model.payments.card.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardMerchant {

    private String merchantId;
    private String mcc;
    private String name;

}

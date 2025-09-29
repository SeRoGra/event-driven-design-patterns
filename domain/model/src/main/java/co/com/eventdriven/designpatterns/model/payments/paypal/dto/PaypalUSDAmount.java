package co.com.eventdriven.designpatterns.model.payments.paypal.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaypalUSDAmount {

    private String currencyCode;
    private String value;

}

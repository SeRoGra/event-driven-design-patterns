package co.com.eventdriven.designpatterns.model.payments.paypal.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaypalCOPAmount {

    private String to;
    private String rate;
    private String convertedValue;

}

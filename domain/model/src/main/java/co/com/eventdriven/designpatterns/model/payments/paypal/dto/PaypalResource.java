package co.com.eventdriven.designpatterns.model.payments.paypal.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaypalResource {

    private PaypalUSDAmount amount;
    private PaypalCOPAmount exchange;


}

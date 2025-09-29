package co.com.eventdriven.designpatterns.model.payments.paypal.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaypalEvent {

    private String eventId;
    private String createTime;
    private PaypalResource resource;

}

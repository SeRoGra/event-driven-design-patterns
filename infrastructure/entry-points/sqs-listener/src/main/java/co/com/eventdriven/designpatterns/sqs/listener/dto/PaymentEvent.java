package co.com.eventdriven.designpatterns.sqs.listener.dto;

import lombok.Data;

@Data
public class PaymentEvent {

    private String paymentMethod;

}

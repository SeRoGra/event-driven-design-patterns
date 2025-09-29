package co.com.eventdriven.designpatterns.sqs.listener.mapper;

import co.com.eventdriven.designpatterns.model.base.PaymentTransactionBase;

public interface EventMapper<T extends PaymentTransactionBase> {

    Boolean validate(String payload);

    T mapper(String payload);

}

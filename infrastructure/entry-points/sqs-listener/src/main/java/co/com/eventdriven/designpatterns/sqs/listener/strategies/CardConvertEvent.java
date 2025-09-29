package co.com.eventdriven.designpatterns.sqs.listener.strategies;

import co.com.eventdriven.designpatterns.model.payments.card.CardPaymentTransaction;
import co.com.eventdriven.designpatterns.sqs.listener.mapper.EventMapper;
import co.com.eventdriven.designpatterns.sqs.listener.mapper.JsonHelperService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static co.com.eventdriven.designpatterns.sqs.listener.enums.TransactionLabels.*;
import static co.com.eventdriven.designpatterns.sqs.listener.mapper.JsonHelperService.isEqual;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardConvertEvent implements EventMapper<CardPaymentTransaction> {

    private final JsonHelperService jsonHelperService;

    @Override
    public Boolean validate(String payload) {
        JsonNode event = jsonHelperService.jsonStringToJsonNode(payload);
        return isEqual(event, TRANSACTION.getKey(), TRANSACTION.getValue()) &&
                isEqual(event, SUBTYPE.getKey(), SUBTYPE.getValue()) &&
                isEqual(event, PHYSICAL_CARD.getKey(), PHYSICAL_CARD.getValue());
    }

    @Override
    public CardPaymentTransaction mapper(String payload) {
        JsonNode event = jsonHelperService.jsonStringToJsonNode(payload);
        return jsonHelperService.jsonNodeToObject(event, CardPaymentTransaction.class);
    }

}

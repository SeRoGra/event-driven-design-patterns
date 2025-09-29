package co.com.eventdriven.designpatterns.sqs.listener.config;

import io.micrometer.context.ContextRegistry;
import jakarta.annotation.PostConstruct;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import static co.com.eventdriven.designpatterns.sqs.listener.constants.OperationMessagesSqs.*;

@Component
public class ReactorContextConfig {

    @PostConstruct
    public void setupContextAccessor() {
        ContextRegistry.getInstance()
                .registerThreadLocalAccessor(SPAN_ID, () -> MDC.get(SPAN_ID),
                        spanId -> MDC.put(SPAN_ID, spanId),
                        () -> MDC.remove(SPAN_ID)
                ).registerThreadLocalAccessor(MESSAGE_ID,
                        () -> MDC.get(MESSAGE_ID),
                        messageId -> MDC.put(MESSAGE_ID, messageId),
                        () -> MDC.remove(MESSAGE_ID)
                );
    }

}

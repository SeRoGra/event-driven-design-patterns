package co.com.eventdriven.designpatterns.exception;

import co.com.eventdriven.designpatterns.enums.TransactionInfoType;
import lombok.Getter;

@Getter
public class TransactionInfoException extends RuntimeException {

    private final TransactionInfoType type;

    public TransactionInfoException(TransactionInfoType type, String message) {
        super(message);
        this.type = type;
    }

    public TransactionInfoException(TransactionInfoType type, String message, Throwable cause) {
        super(message, cause);
        this.type = type;
    }

}

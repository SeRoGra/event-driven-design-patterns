package co.com.eventdriven.designpatterns.enums;

import co.com.eventdriven.designpatterns.exception.TransactionInfoException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionInfoType {

    SUCCESS("200", "Success"),
    BAD_REQUEST("400", "Bad Request"),
    NOT_FOUND("404", "Not Found"),
    NOT_IMPLEMENTED("501", "Not Implemented");

    private final String code;
    private final String message;

    public TransactionInfoException build() {
        return new TransactionInfoException(this, this.message);
    }

    public TransactionInfoException build(String message) {
        return new TransactionInfoException(this, this.message.concat(" :: ").concat(message));
    }

    public TransactionInfoException build(String message, Throwable cause) {
        return new TransactionInfoException(this, this.message.concat(" :: ").concat(message), cause);
    }

    public TransactionInfoException build(Throwable cause) {
        return new TransactionInfoException(this, this.message, cause);
    }

}

package co.com.eventdriven.designpatterns.exception;

import co.com.eventdriven.designpatterns.enums.InfrastructureType;
import lombok.Getter;

@Getter
public class InfrastructureException extends RuntimeException {

    private final InfrastructureType type;

    public InfrastructureException(InfrastructureType type, String message) {
        super(message);
        this.type = type;
    }

    public InfrastructureException(InfrastructureType type, String message, Throwable cause) {
        super(message, cause);
        this.type = type;
    }

}

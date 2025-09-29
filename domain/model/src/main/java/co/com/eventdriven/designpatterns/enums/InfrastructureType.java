package co.com.eventdriven.designpatterns.enums;

import co.com.eventdriven.designpatterns.exception.InfrastructureException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InfrastructureType {

    INTERNAL_SERVER_ERROR("500", "Internal Server Error"),
    JSON_HELPER_ERROR(INTERNAL_SERVER_ERROR.code,"Technical error with JsonHelper");

    private final String code;
    private final String message;

    public InfrastructureException build() {
        return new InfrastructureException(this, this.message);
    }

    public InfrastructureException build(String message) {
        return new InfrastructureException(this, this.message.concat(" :: ").concat(message));
    }

    public InfrastructureException build(String message, Throwable cause) {
        return new InfrastructureException(this, this.message.concat(" :: ").concat(message), cause);
    }

    public InfrastructureException build(Throwable cause) {
        return new InfrastructureException(this, this.message, cause);
    }

}

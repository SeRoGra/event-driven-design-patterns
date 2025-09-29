package co.com.eventdriven.designpatterns.model.transaction;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class TransactionInfo {

    private String dni;
    private String transactionId;
    private String commerce;
    private String creationDate;
    private String status;

}

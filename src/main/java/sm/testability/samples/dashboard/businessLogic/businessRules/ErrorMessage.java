package sm.testability.samples.dashboard.businessLogic.businessRules;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class ErrorMessage {

    private String timestamp;
    private int status;
    private String error;
    private String message;

    public ErrorMessage(HttpStatus status, String message, Date currentDate) {
        this.timestamp = DateFormatter.convertDateTimeToString(currentDate);
        this.status = status.value();
        this.error = status.name();
        this.message = message;
    }
}
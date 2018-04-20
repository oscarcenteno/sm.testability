package sm.testability.samples.dashboard.businessLogic;

import org.springframework.http.HttpStatus;
import sm.testability.samples.dashboard.businessLogic.businessRules.ErrorMessage;

import java.util.Date;

public class ProjectNotFoundErrorCreation {
    public static ErrorMessage get(int projectId, Date currentDate){
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message =  "Project " + projectId + " could not be found";

        return new ErrorMessage(status, message, currentDate);
    }
}
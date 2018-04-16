package sm.testability.samples.createProject.responses;

import lombok.Data;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

@Data
public class Project {
    private int id;
    private String name;
    private LocalDateTime creationTime;

    public Project(int id, String name, LocalDateTime currentDate) {
        this.id = id;
        this.name = name;
        this.creationTime = currentDate;
    }

    public Project(String name, LocalDateTime currentDateTime) {
        this.name = name;
        this.creationTime = currentDateTime;
    }
}

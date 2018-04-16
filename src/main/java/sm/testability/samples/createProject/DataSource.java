package sm.testability.samples.createProject;

import org.springframework.stereotype.Component;
import sm.testability.samples.createProject.responses.Project;

import java.time.LocalDateTime;
import java.util.List;

@Component
public interface DataSource {
    // "retrieve" methods
    LocalDateTime getCurrentDateTime();
    List<String> getExistingProjectNames();

    // "send" methods
    Project saveProject(Project project);
}

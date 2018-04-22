package sm.testability.samples.createProject.dataAccess;

import org.springframework.stereotype.Component;
import sm.testability.samples.createProject.responses.Project;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataSourceImplementation implements DataSource {
    @Override
    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    @Override
    public List<String> getExistingProjectNames() {
        return null;
    }

    @Override
    public Project saveProject(Project project) {
        return null;
    }
}

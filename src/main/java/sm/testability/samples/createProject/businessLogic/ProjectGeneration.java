package sm.testability.samples.createProject.businessLogic;

import sm.testability.samples.createProject.requests.NewProject;
import sm.testability.samples.createProject.responses.Project;

import java.time.LocalDateTime;

public class ProjectGeneration {
    public static Project generate(NewProject newProject, LocalDateTime currentDateTime) {
        String name = newProject.getName();

        return new Project(name, currentDateTime);
    }
}

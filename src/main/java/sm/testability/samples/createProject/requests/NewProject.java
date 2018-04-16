package sm.testability.samples.createProject.requests;

import lombok.*;

@Data
public class NewProject {

    private String name;

    public NewProject(String name) {
        this.name = name;
    }
}

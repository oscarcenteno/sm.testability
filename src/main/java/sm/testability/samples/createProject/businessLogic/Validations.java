package sm.testability.samples.createProject.businessLogic;

import sm.testability.samples.createProject.requests.NewProject;

import java.util.List;

public class Validations {
    public static void validate(NewProject newProject, List<String> names) throws IllegalArgumentException {

        if (names.contains(newProject.getName())){
            throw new IllegalArgumentException("Name should not exist previously");
        }
    }
}

package sm.testability.samples.createProject.businessLogic.validations;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import sm.testability.samples.createProject.businessLogic.Validations;
import sm.testability.samples.createProject.requests.NewProject;

import java.util.ArrayList;
import java.util.List;

public class ValidateTests {

    @Test
    public void validDataThrowsNoException(){
        NewProject newProject = new NewProject("Jupiter");
        List<String> names = new ArrayList<>();

        Validations.validate(newProject, names);
    }

    @Test(expected = IllegalArgumentException.class)
    public void existingNameThrowsException(){
        NewProject newProject = new NewProject("Jupiter");
        List<String> names = new ArrayList<>();
        names.add("Jupiter");

        Validations.validate(newProject, names);
    }
}

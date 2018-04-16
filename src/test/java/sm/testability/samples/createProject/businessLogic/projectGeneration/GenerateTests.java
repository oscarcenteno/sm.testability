package sm.testability.samples.createProject.businessLogic.projectGeneration;

import org.junit.Test;
import sm.testability.samples.createProject.businessLogic.ProjectGeneration;
import sm.testability.samples.createProject.requests.NewProject;
import sm.testability.samples.createProject.responses.Project;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class GenerateTests {

    @Test
    public void shouldGenerateCompleteProject(){
        LocalDateTime date= LocalDateTime.of(2018, 04, 25, 12, 55);
        Project expected = new Project("Jupiter", date);

        NewProject newProject = new NewProject("Jupiter");
        Project actual = ProjectGeneration.generate(newProject, date);

        assertEquals(expected, actual);
    }
}
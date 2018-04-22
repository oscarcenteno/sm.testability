package sm.testability.samples.createProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sm.testability.samples.createProject.businessLogic.ProjectGeneration;
import sm.testability.samples.createProject.businessLogic.Validations;
import sm.testability.samples.createProject.dataAccess.DataSource;
import sm.testability.samples.createProject.requests.NewProject;
import sm.testability.samples.createProject.responses.Project;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/projects")
public class CoordinationAlgorithm {
    @Autowired private DataSource dataSource;

    @PostMapping("/")
    public ResponseEntity<Object> postNewProject(NewProject newProject){
        // get required data for external data sources
        LocalDateTime time = dataSource.getCurrentDateTime();
        List<String> names = dataSource.getExistingProjectNames();
        ResponseEntity<Object> response;

        try {
            // validate request
            Validations.validate(newProject, names);

            // generate new project
            Project project = ProjectGeneration.generate(newProject, time);

            // save new project
            project = dataSource.saveProject(project);

            response = new ResponseEntity<>(project, HttpStatus.OK);
        }
        catch (IllegalArgumentException ex) {
            response = new ResponseEntity<>(newProject, HttpStatus.BAD_REQUEST);
        }

        return response;
    }
}
package sm.testability.samples.dashboard;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import sm.testability.samples.dashboard.businessLogic.ProjectDTOCreation;
import sm.testability.samples.dashboard.businessLogic.ProjectNotFoundErrorCreation;
import sm.testability.samples.dashboard.businessLogic.businessRules.ErrorMessage;
import sm.testability.samples.dashboard.businessLogic.inputs.Project;
import sm.testability.samples.dashboard.businessLogic.response.ProjectDTO;
import sm.testability.samples.dashboard.dataAccess.DashboardProjectRepository;
import sm.testability.samples.dashboard.dataAccess.Dependencies;

import java.text.ParseException;
import java.util.Date;

@RestController
@RequestMapping(value = "/api/v1/projects")
public class DashboardController {

    @Autowired
    private DashboardProjectRepository repository;
    @Autowired
    private Dependencies dependencies;

    @GetMapping("/{projectId}/dashboard")
    public ResponseEntity<Object> findDashboardByProjectId(@PathVariable("projectId") final int projectId) throws ParseException {
        Logger logger = LoggerFactory.getLogger(this.getClass());

        logger.info("findDashboardByProjectId received: " + projectId);
        Project project = repository.getOne(projectId);

        ResponseEntity<Object> response;

        if (project == null){
            Date currentDate = dependencies.getCurrentDate();
            ErrorMessage errorMessage = ProjectNotFoundErrorCreation.get(projectId, currentDate);

            response = new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
        else
        {
            ProjectDTO projectDTO = ProjectDTOCreation.get(project);

            response = new ResponseEntity<>(projectDTO, HttpStatus.OK);
        }


        logger.info("findDashboardByProjectId returned {}", response);

        return response;
    }
}
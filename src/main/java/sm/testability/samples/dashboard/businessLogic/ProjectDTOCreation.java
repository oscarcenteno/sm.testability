package sm.testability.samples.dashboard.businessLogic;

import sm.testability.samples.dashboard.businessLogic.inputs.DashboardInputs;
import sm.testability.samples.dashboard.businessLogic.inputs.Project;
import sm.testability.samples.dashboard.businessLogic.response.CycleSnapDTO;
import sm.testability.samples.dashboard.businessLogic.response.ProjectDTO;

import java.text.ParseException;
import java.util.LinkedHashSet;
import java.util.Set;

public class ProjectDTOCreation {

    public static ProjectDTO get(Project project) throws ParseException {
        ProjectDTO projectDTO = new ProjectDTO();

        // name
        projectDTO.setName(project.getName());

        // cycles
        if (project.getCycleSnapSet().isEmpty()) {
            Set<CycleSnapDTO> cycleList = new LinkedHashSet<>();
            projectDTO.setCycleSnaps(cycleList);
        } else {
            Set<CycleSnapDTO> cycleList = CycleSnapDTOListCreation.get(project);
            projectDTO.setCycleSnaps(cycleList);
        }

        return projectDTO;
    }
}
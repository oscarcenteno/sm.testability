package sm.testability.samples.dashboard.businessLogic;

import sm.testability.samples.dashboard.businessLogic.businessRules.CycleSnapsOperations;
import sm.testability.samples.dashboard.businessLogic.inputs.CycleSnap;
import sm.testability.samples.dashboard.businessLogic.inputs.Project;
import sm.testability.samples.dashboard.businessLogic.response.CycleSnapDTO;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CycleSnapDTOListCreation {

    public static Set<CycleSnapDTO> get(Project project) throws ParseException {
        List<String> dates = project.getCyclesStartDates();
        Date firstCycleSnapStartDate = CycleSnapsOperations.findTheFirstDate(dates);

        return generateTheList(project, firstCycleSnapStartDate);
    }

    private static Set<CycleSnapDTO> generateTheList(Project project, Date firstCycleSnapStartDate) throws ParseException {
        Set<CycleSnapDTO> cycleList = new LinkedHashSet<>();
        for (CycleSnap cycleSnap : project.getCycleSnapSet()) {
            CycleSnapDTO cycleSnapDTO = CycleSnapDTOCreation.get(
                    firstCycleSnapStartDate,
                    cycleSnap,
                    project.getIncidentsAsBusinessObjects(),
                    project.getReleaseSnaps());
            cycleList.add(cycleSnapDTO);
        }
        return cycleList;
    }
}
package sm.testability.samples.dashboard.businessLogic.businessRules;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class RelatedIncidentsCalculation {

    public static String determineRelatedIncidents(List<IncidentReportBO> incidentReports,
                                                   String cycleSnapEndDateAsText,
                                                   List<String> releaseDates) throws ParseException {
        Date cycleSnapDate = DateFormatter.convertDateStringToDate(cycleSnapEndDateAsText);
        Date releaseToCompare = DaysSinceLastReleaseCalculation.getLastReleaseDate(releaseDates, cycleSnapDate);

        if (releaseToCompare == null)
            return "No releases yet";
        else {

            List<IncidentReportBO> foundReports = new LinkedList();
//              2. Get the incident reports between release date and cycleSnap End date
            for (IncidentReportBO report : incidentReports) {
                String dateAsText = report.getIncidentsDate();
                Date reportDate = DateFormatter.convertDateStringToDate(dateAsText);

                if (!reportDate.after(cycleSnapDate) && !reportDate.before(releaseToCompare))
                    foundReports.add(report);
            }

            if (foundReports.isEmpty())
                return "No reports available";
            else {
                int total = 0;
                for (IncidentReportBO report : foundReports) {
                    total += report.getTotalIncidents();
                }
                return String.valueOf(total);
            }
        }
    }
}


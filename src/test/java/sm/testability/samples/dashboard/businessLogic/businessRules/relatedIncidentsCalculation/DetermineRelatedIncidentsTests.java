package sm.testability.samples.dashboard.businessLogic.businessRules.relatedIncidentsCalculation;

import org.junit.*;
import sm.testability.samples.dashboard.businessLogic.businessRules.*;
import java.text.ParseException;
import java.util.*;

public class DetermineRelatedIncidentsTests {

    List<IncidentReportBO> incidentReports;
    List<String> releaseDates;
    String cycleEndDate;

    @Before
    public void initialize(){
        incidentReports = new LinkedList();
        incidentReports.add(new IncidentReportBO("2017-02-07", 2));
        incidentReports.add(new IncidentReportBO("2017-02-09", 3));
        incidentReports.add(new IncidentReportBO("2017-02-21", 1));
        incidentReports.add(new IncidentReportBO("2017-03-02", 4));
        incidentReports.add(new IncidentReportBO("2017-03-02", 1));
        incidentReports.add(new IncidentReportBO("2017-03-14", 1));
        incidentReports.add(new IncidentReportBO("2017-03-16", 1));

        releaseDates = new LinkedList();
        releaseDates.add("2017-02-07");
        releaseDates.add("2017-03-02");
    }

    @Test
    public void thereAreNoReleasesYetBeforeTheCurrentCycleEndDate() throws ParseException {
        String expected = "No releases yet";

        cycleEndDate = "2017-01-30";
        String days = RelatedIncidentsCalculation.determineRelatedIncidents(incidentReports, cycleEndDate, releaseDates);

        Assert.assertEquals(expected, days);
    }

    @Test
    public void thereIsOneReleaseBeforeAndAReportIsTheSameAsTheReleaseDate() throws ParseException {
        String expected = "5";

        cycleEndDate = "2017-02-14";
        String days = RelatedIncidentsCalculation.determineRelatedIncidents(incidentReports, cycleEndDate, releaseDates);

        Assert.assertEquals(expected, days);
    }

    @Test
    public void thereIsOneReleaseBeforeAndShouldAccumulateReports() throws ParseException {
        String expected = "6";

        cycleEndDate = "2017-02-28";
        String days = RelatedIncidentsCalculation.determineRelatedIncidents(incidentReports, cycleEndDate, releaseDates);

        Assert.assertEquals(expected, days);
    }

    @Test
    public void thereAreTwoReleasesBeforeAndShouldAccumulateReportsForTheMostRecentOnly() throws ParseException {
        String expected = "6";

        cycleEndDate = "2017-03-15";
        String days = RelatedIncidentsCalculation.determineRelatedIncidents(incidentReports, cycleEndDate, releaseDates);

        Assert.assertEquals(expected, days);
    }

    @Test
    public void  dashboardShowsNoDataIfThereAreNoProductionIncidents() throws ParseException {
        String expected = "No reports available";

        cycleEndDate = "2017-02-14";
        incidentReports = new LinkedList();
        String days = RelatedIncidentsCalculation.determineRelatedIncidents(incidentReports, cycleEndDate, releaseDates);

        Assert.assertEquals(expected, days);
    }
}
package sm.testability.samples.dashboard.businessLogic.businessRules.daysSinceLastReleaseCalculation;

import org.junit.*;
import sm.testability.samples.dashboard.businessLogic.businessRules.DaysSinceLastReleaseCalculation;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class DetermineDaysTests {
    List<String> releaseDates;
    Date firstCycleStartDate;

    @Before
    public void initialize() {
        releaseDates = new LinkedList();
        releaseDates.add("2017-04-02");
        releaseDates.add("2017-03-02");
        releaseDates.add("2017-02-07");

        firstCycleStartDate = new Date(117, 0, 25);
    }

    @Test
    public void thereAreNoReleases() throws ParseException {
        String expected = "5";

        String cycleSnapEndDate = "2017-01-30";
        String days = DaysSinceLastReleaseCalculation.determineDays(firstCycleStartDate, cycleSnapEndDate, releaseDates);

        Assert.assertEquals(expected, days);
    }

    @Test
    public void thereIsAReleaseBefore() throws ParseException {
        String expected = "7";

        String cycleSnapEndDate = "2017-02-14";
        String days = DaysSinceLastReleaseCalculation.determineDays(firstCycleStartDate, cycleSnapEndDate, releaseDates);

        Assert.assertEquals(expected, days);
    }

    @Test
    public void thereAreTwoReleasesBefore() throws ParseException {
        String expected = "13";

        String cycleSnapEndDate = "2017-03-15";
        String days = DaysSinceLastReleaseCalculation.determineDays(firstCycleStartDate, cycleSnapEndDate, releaseDates);

        Assert.assertEquals(expected, days);
    }
}
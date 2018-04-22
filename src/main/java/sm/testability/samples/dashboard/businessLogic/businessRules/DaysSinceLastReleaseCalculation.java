package sm.testability.samples.dashboard.businessLogic.businessRules;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

public class DaysSinceLastReleaseCalculation {

    public static String determineDays(Date firstCycleStartDate, String cycleSnapEndDateAsString, List<String> releaseDates) throws ParseException {
        Date cycleSnapEndDate = DateFormatter.convertDateStringToDate(cycleSnapEndDateAsString);

        Date releaseToCompare = getLastReleaseDate(releaseDates, cycleSnapEndDate);

        DateTime dateToCompare;
        if (releaseToCompare == null) {
            dateToCompare = new DateTime(firstCycleStartDate);
        } else {
            dateToCompare = new DateTime(releaseToCompare);
        }

        DateTime endDateTime = new DateTime(cycleSnapEndDate);
        int days = Days.daysBetween(dateToCompare, endDateTime).getDays();

        return "" + days;
    }

    public static Date getLastReleaseDate(List<String> releaseDates, Date cycleSnapEndDate) throws ParseException {
        TreeSet<Date> treeAdd = new TreeSet<>();
        for (String releaseDate : releaseDates) {
            Date newReleaseDate = DateFormatter.convertDateStringToDate(releaseDate);
            treeAdd.add(newReleaseDate);
        }

        return treeAdd.floor(cycleSnapEndDate);
    }
}
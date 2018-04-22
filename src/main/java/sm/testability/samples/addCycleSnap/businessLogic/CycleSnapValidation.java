package sm.testability.samples.addCycleSnap.businessLogic;

import sm.testability.samples.addCycleSnap.businessLogic.inputs.CycleSnap;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class CycleSnapValidation {

    public static void validate(CycleSnap newCycleSnap, List<CycleSnap> existingCycleSnaps) throws ParseException {
        if (newCycleSnap.getProjectId() <= 0) {
            throw new IllegalArgumentException("Project Id is required");
        }

        if (newCycleSnap.getStartDate().isEmpty()) {
            throw new IllegalArgumentException("Start Date is required");
        }

        if (newCycleSnap.getEndDate().isEmpty()) {
            throw new IllegalArgumentException("End Date is required");
        }

        DateTime startDateTime = new DateTime(DateFormatter.convertDateStringToDate(newCycleSnap.getStartDate()));
        DateTime endDateTime = new DateTime(DateFormatter.convertDateStringToDate(newCycleSnap.getEndDate()));
        Date startDate = DateFormatter.convertDateStringToDate(newCycleSnap.getStartDate());
        Date endDate = DateFormatter.convertDateStringToDate(newCycleSnap.getEndDate());

        if (newCycleSnap.getCycleSnapName().isEmpty()) {
            throw new IllegalArgumentException("Cycle name is required");
        }
        if (newCycleSnap.getCycleSnapName().length() > 200) {
            throw new IllegalArgumentException("Cycle name can not be greater than 200 characters");
        }
        if (newCycleSnap.getStartDate().isEmpty()) {
            throw new IllegalArgumentException("Start Date is required");
        }
        if (newCycleSnap.getEndDate().isEmpty()) {
            throw new IllegalArgumentException("End Date is required");
        }
        if (!startDate.before(endDate)) {
            throw new IllegalArgumentException("Start Date should precede End Date");
        }
        if (newCycleSnap.getTargetedPoints() < 0) {
            throw new IllegalArgumentException("Min number for Targeted Points is 0");
        }
        if (newCycleSnap.getTargetedPoints() > 10000) {
            throw new IllegalArgumentException("Max number for Targeted Points is 10,000");
        }
        if (newCycleSnap.getAchievedPoints() < 0) {
            throw new IllegalArgumentException("Min number for Achieved Points is 0");
        }
        if (newCycleSnap.getAchievedPoints() > 10000) {
            throw new IllegalArgumentException("Max number for Achieved Points is 10,000");
        }
        if (newCycleSnap.getIsMoodAvailable() && (newCycleSnap.getMoodAverage() < 1)) {
            throw new IllegalArgumentException("Min number for Mood points is 1");
        }

        if (newCycleSnap.getIsMoodAvailable() && (newCycleSnap.getMoodAverage() > 3)) {
            throw new IllegalArgumentException("Mood points max is 3");
        }
        if (newCycleSnap.getIsWasteAvailable() && (newCycleSnap.getTeamCapacity() < 1)) {
            throw new IllegalArgumentException("Team capacity min is 1");
        }
        if (newCycleSnap.getIsWasteAvailable() && (newCycleSnap.getTeamCapacity() > 10000)) {
            throw new IllegalArgumentException("Team capacity max is 10,000 ");
        }
        if (newCycleSnap.getIsWasteAvailable() && newCycleSnap.getWasteDays() < 0) {
            throw new IllegalArgumentException("Waste days min is 0");
        }
        if (newCycleSnap.getIsWasteAvailable() && newCycleSnap.getWasteDays() > newCycleSnap.getTeamCapacity()) {
            throw new IllegalArgumentException("Waste days max is the team's capacity");
        }

        //Cycle dates shouldn't overlap
        Interval newInterval = new Interval(startDateTime, endDateTime);
        for (CycleSnap existingCycle : existingCycleSnaps) {
            DateTime cycleStartDate = new DateTime(DateFormatter.convertDateStringToDate(existingCycle.getStartDate()));
            DateTime cycleEndDate = new DateTime(DateFormatter.convertDateStringToDate(existingCycle.getEndDate()));

            Interval existingInterval = new Interval(cycleStartDate, cycleEndDate);

            if (newInterval.overlaps(existingInterval)) {
                throw new IllegalArgumentException("The new cycle dates overlap with an existing cycle");
            }
        }
    }
}
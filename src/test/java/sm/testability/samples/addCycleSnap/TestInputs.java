package sm.testability.samples.addCycleSnap;

import sm.testability.samples.addCycleSnap.businessLogic.inputs.CycleSnap;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class TestInputs {
    public static CycleSnap getValidCycleSnap() {
        CycleSnap newCycle = new CycleSnap();
        newCycle.setProjectId(12345);
        newCycle.setCycleSnapName("1");
        newCycle.setStartDate("2016-11-15");
        newCycle.setEndDate("2016-11-21");
        newCycle.setTargetedPoints(8);
        newCycle.setAchievedPoints(8);
        newCycle.setIsMoodAvailable(true);
        newCycle.setMoodAverage(3);
        newCycle.setIsWasteAvailable(true);
        newCycle.setWasteDays(5.00);
        newCycle.setTeamCapacity(100.00);

        return newCycle;
    }

    public static List<CycleSnap> getExistingCycles() {
        CycleSnap cycle1 = new CycleSnap();
        cycle1.setCycleSnapName("1");
        cycle1.setStartDate("2016-10-03");
        cycle1.setEndDate("2016-10-24");

        CycleSnap cycle2 = new CycleSnap();
        cycle2.setCycleSnapName("2");
        cycle2.setStartDate("2016-10-26");
        cycle2.setEndDate("2016-11-14");

        List<CycleSnap> existingCycleSnaps = new LinkedList<>();
        existingCycleSnaps.add(cycle1);
        existingCycleSnaps.add(cycle2);

        return existingCycleSnaps;
    }

    static Date getCurrentDate() {
        return new Date(117, 07, 31);
    }
}
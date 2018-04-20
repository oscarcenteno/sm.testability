package sm.testability.samples.dashboard.businessLogic;


import sm.testability.samples.dashboard.businessLogic.businessRules.*;
import sm.testability.samples.dashboard.businessLogic.inputs.CycleSnap;
import sm.testability.samples.dashboard.businessLogic.inputs.ReleaseSnap;
import sm.testability.samples.dashboard.businessLogic.response.CycleSnapDTO;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CycleSnapDTOCreation {
    public static CycleSnapDTO get(Date firstCycleSnapStartDate,
                                   CycleSnap cycleSnap,
                                   List<IncidentReportBO> reports,
                                   List<ReleaseSnap> releaseSnaps) throws ParseException {
        CycleSnapDTO cycleSnapDTO = new CycleSnapDTO();

        setBasicInfo(cycleSnap, cycleSnapDTO);
        setTacInfo(cycleSnap, cycleSnapDTO);

        List<String> releaseDates = getReleaseDates(releaseSnaps);
        setIncidentsInfo(cycleSnap, reports, cycleSnapDTO, releaseDates);

        setReleasesInfo(firstCycleSnapStartDate, cycleSnap, releaseSnaps, cycleSnapDTO, releaseDates);
        setWasteInfo(cycleSnap, cycleSnapDTO);
        setMoodInfo(cycleSnap, cycleSnapDTO);

        return cycleSnapDTO;
    }

    private static void setMoodInfo(CycleSnap cycleSnap, CycleSnapDTO cycleSnapDTO) {
        boolean isMoodAvailable = cycleSnap.isMoodAvailable();
        double moodAverage = cycleSnap.getMoodAverage();
        String mood = MoodCalculation.calculateMood(isMoodAvailable, moodAverage);

        cycleSnapDTO.setMood(mood);
    }

    private static void setWasteInfo(CycleSnap cycleSnap, CycleSnapDTO cycleSnapDTO) {
        double teamCapacity = cycleSnap.getTeamCapacity();
        cycleSnapDTO.setTeamCapacity(teamCapacity);

        double wasteDays = cycleSnap.getWasteDays();
        cycleSnapDTO.setWasteDays(wasteDays);

        boolean isWasteAvailable = cycleSnap.isWasteAvailable();
        String wastePercentage = WasteMeasureCalculation.calculateWaste(teamCapacity, wasteDays, isWasteAvailable);

        cycleSnapDTO.setWastePercentage(wastePercentage);
    }

    private static void setReleasesInfo(Date firstCycleSnapStartDate, CycleSnap cycleSnap, List<ReleaseSnap> releaseSnaps, CycleSnapDTO cycleSnapDTO, List<String> releaseDates) throws ParseException {
        String daysSinceLastRelease = DaysSinceLastReleaseCalculation.determineDays(firstCycleSnapStartDate, cycleSnap.getEndDate(), releaseDates);
        cycleSnapDTO.setDaysSinceLastRelease(daysSinceLastRelease);

        LastReleaseInfo lastRelease = LastReleaseOperations.getLastRelease(cycleSnap.getEndDate(), releaseSnaps);
        String lastReleaseName = lastRelease.getLastReleaseName();
        cycleSnapDTO.setLastReleaseName(lastReleaseName);

        String lastReleaseDate = lastRelease.getLastReleaseDate();
        cycleSnapDTO.setLastReleaseDate(lastReleaseDate);
    }

    private static void setIncidentsInfo(CycleSnap cycleSnap, List<IncidentReportBO> reports, CycleSnapDTO cycleSnapDTO, List<String> releaseDates) throws ParseException {
        String relatedIncidents = RelatedIncidentsCalculation.determineRelatedIncidents(reports, cycleSnap.getEndDate(), releaseDates);
        cycleSnapDTO.setRelatedIncidents(relatedIncidents);
    }

    private static void setTacInfo(CycleSnap cycleSnap, CycleSnapDTO cycleSnapDTO) {
        int targeted = cycleSnap.getTargetedPoints();
        cycleSnapDTO.setTargetedPoints(targeted);

        int achieved = cycleSnap.getAchievedPoints();
        cycleSnapDTO.setAchievedPoints(achieved);

        String tac = TacCalculation.calculateTac(targeted, achieved);
        cycleSnapDTO.setTac(tac);
    }

    private static void setBasicInfo(CycleSnap cycleSnap, CycleSnapDTO cycleSnapDTO) {
        cycleSnapDTO.setCycleSnapName(cycleSnap.getCycleSnapName());
        cycleSnapDTO.setStartDate(cycleSnap.getStartDate());
        cycleSnapDTO.setEndDate(cycleSnap.getEndDate());
    }

    private static List<String> getReleaseDates(List<ReleaseSnap> releaseSnaps) {
        List<String> releaseDates = new LinkedList<>();
        for (ReleaseSnap releaseSnap : releaseSnaps) {
            releaseDates.add(releaseSnap.getReleaseDate());
        }

        return releaseDates;
    }
}

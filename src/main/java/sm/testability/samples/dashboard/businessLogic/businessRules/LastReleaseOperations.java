package sm.testability.samples.dashboard.businessLogic.businessRules;
import java.text.ParseException;
import sm.testability.samples.dashboard.businessLogic.inputs.ReleaseSnap;

import java.util.Date;
import java.util.List;
import java.util.TreeSet;

public class LastReleaseOperations {

    public static LastReleaseInfo getLastRelease(String cycleSnapEndDate,
                                                 List<ReleaseSnap> releases) throws ParseException {
        LastReleaseInfo lastRelease = new LastReleaseInfo();
        Date cycleEndDate = DateFormatter.convertDateStringToDate(cycleSnapEndDate);
        TreeSet<ReleaseSnap> releaseDateComparison = new TreeSet<ReleaseSnap>(new LatestReleaseDateComparison());


        if(releases.isEmpty()) {
            lastRelease.setLastReleaseDate("No releases yet");
            lastRelease.setLastReleaseName("No releases yet");
        }
        else{

            // Create the sorted list
            for(ReleaseSnap release:releases){
                releaseDateComparison.add(release);
            }

            // Get the latest release
            ReleaseSnap pointInTime = new ReleaseSnap();
            pointInTime.setReleaseDate(cycleSnapEndDate);
            ReleaseSnap lastReleaseSnap = releaseDateComparison.floor(pointInTime);

            // Format the returned info
            if (lastReleaseSnap == null) {
                lastRelease.setLastReleaseDate("No releases yet");
                lastRelease.setLastReleaseName("No releases yet");
            }
            else{
                lastRelease.setLastReleaseDate(lastReleaseSnap.getReleaseDate());
                lastRelease.setLastReleaseName(lastReleaseSnap.getReleaseName());
            }
        }

        return lastRelease;
    }
}


package sm.testability.samples.dashboard.businessLogic.businessRules.lastReleaseOperations;

import org.junit.*;
import sm.testability.samples.dashboard.businessLogic.businessRules.*;
import sm.testability.samples.dashboard.businessLogic.inputs.ReleaseSnap;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

public class GetLastReleaseTests {

    @Test
    public void thereAreNoReleases() throws ParseException {
        LastReleaseInfo expected = new LastReleaseInfo();
        expected.setLastReleaseDate("No releases yet");
        expected.setLastReleaseName("No releases yet");

        String cycleEndDate = "2017-01-30";
        List<ReleaseSnap> releases = new LinkedList<>();
        LastReleaseInfo actual = LastReleaseOperations.getLastRelease(cycleEndDate, releases);

        Assert.assertEquals(expected, actual);
    }

    private List<ReleaseSnap> getNoReleasesBeforeAndSomeAfter() {
        List<ReleaseSnap> releases = new LinkedList<>();
        ReleaseSnap releaseAfter = new ReleaseSnap();
        releaseAfter.setReleaseDate("2017-03-02");
        releaseAfter.setReleaseName("Second update");

        ReleaseSnap releaseBefore = new ReleaseSnap();
        releaseBefore.setReleaseDate("2017-02-07");
        releaseBefore.setReleaseName("First version");

        releases.add(releaseAfter);
        releases.add(releaseBefore);
        return releases;
    }

    @Test
    public void thereAreNoReleasesBeforeButOthersAfter() throws ParseException {
        LastReleaseInfo expected = new LastReleaseInfo();
        expected.setLastReleaseName("No releases yet");
        expected.setLastReleaseDate("No releases yet");

        String cycleEndDate = "2017-01-30";
        List<ReleaseSnap> releases = getNoReleasesBeforeAndSomeAfter();
        LastReleaseInfo actual = LastReleaseOperations.getLastRelease(cycleEndDate, releases);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void thereIsAReleaseBefore() throws ParseException {
        LastReleaseInfo expected = new LastReleaseInfo();
        expected.setLastReleaseName("First version");
        expected.setLastReleaseDate("2017-02-07");

        String cycleEndDate = "2017-02-14";
        List<ReleaseSnap> releases = new LinkedList<>();
        ReleaseSnap releaseOne = new ReleaseSnap();
        releaseOne.setReleaseDate("2017-02-07");
        releaseOne.setReleaseName("First version");
        releases.add(releaseOne);
        LastReleaseInfo actual = LastReleaseOperations.getLastRelease(cycleEndDate, releases);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void thereIsAReleaseBeforeAndAfter() throws ParseException {
        LastReleaseInfo expected = new LastReleaseInfo();
        expected.setLastReleaseName("First version");
        expected.setLastReleaseDate("2017-02-07");


        String cycleEndDate = "2017-02-28";
        List<ReleaseSnap> releases = new LinkedList<>();
        ReleaseSnap releaseAfter = new ReleaseSnap();
        releaseAfter.setReleaseDate("2017-03-02");
        releaseAfter.setReleaseName("Second update");
        ReleaseSnap releaseBefore = new ReleaseSnap();
        releaseBefore.setReleaseDate("2017-02-07");
        releaseBefore.setReleaseName("First version");
        releases.add(releaseAfter);
        releases.add(releaseBefore);

        LastReleaseInfo actual = LastReleaseOperations.getLastRelease(cycleEndDate, releases);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void thereAreTwoReleasesBeforeAndOneAfter() throws ParseException {
        String cycleEndDate = "2017-03-15";

        ReleaseSnap releaseBefore2 = new ReleaseSnap();
        releaseBefore2.setReleaseDate("2017-02-07");
        releaseBefore2.setReleaseName("First version");

        List<ReleaseSnap> releases = new LinkedList<>();
        ReleaseSnap releaseBefore1 = new ReleaseSnap();
        releaseBefore1.setReleaseDate("2017-03-02");
        releaseBefore1.setReleaseName("Second update");

        ReleaseSnap releaseAfter = new ReleaseSnap();
        releaseAfter.setReleaseDate("2017-04-02");
        releaseAfter.setReleaseName("Third update");

        releases.add(releaseBefore1);
        releases.add(releaseBefore2);
        releases.add(releaseAfter);

        LastReleaseInfo expected = new LastReleaseInfo();
        expected.setLastReleaseName("Second update");
        expected.setLastReleaseDate("2017-03-02");

        LastReleaseInfo actual = LastReleaseOperations.getLastRelease(cycleEndDate, releases);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void thereIsARaeleaseTheSameDayOfCycleEndDate() throws ParseException {
        String cycleEndDate = "2017-06-14";
        List<ReleaseSnap> releases = new LinkedList<>();
        ReleaseSnap releaseOne = new ReleaseSnap();
        releaseOne.setReleaseDate("2017-06-14");
        releaseOne.setReleaseName("Other version");
        releases.add(releaseOne);

        LastReleaseInfo expected = new LastReleaseInfo();
        expected.setLastReleaseName("Other version");
        expected.setLastReleaseDate("2017-06-14");

        LastReleaseInfo actual = LastReleaseOperations.getLastRelease(cycleEndDate, releases);

        Assert.assertEquals(expected, actual);
    }
}
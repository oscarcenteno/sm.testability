package sm.testability.samples.dashboard.businessLogic.businessRules.moodCalculation;

import org.junit.*;
import sm.testability.samples.dashboard.businessLogic.businessRules.MoodCalculation;

public class CalculateMoodTests {
    boolean isMoodAvailable;
    double moodAverage;
    String howIsMood;

    @Test
    public void isThereAnyMoodAvailable() {
        String expected = "3.00";

        moodAverage = 3;
        isMoodAvailable = true;
        howIsMood = MoodCalculation.calculateMood(isMoodAvailable, moodAverage);

        Assert.assertEquals(expected, howIsMood);
    }

    @Test
    public void MoodCanHaveTwoDecimals() {
        String expected = "2.50";

        moodAverage = 2.5;
        isMoodAvailable = true;
        howIsMood = MoodCalculation.calculateMood(isMoodAvailable, moodAverage);

        Assert.assertEquals(expected, howIsMood);
    }

    @Test
    public void moodCanHaveTwoDecimalsOnly() {
        String expected = "2.60";

        moodAverage = 2.599;
        isMoodAvailable = true;
        howIsMood = MoodCalculation.calculateMood(isMoodAvailable, moodAverage);

        Assert.assertEquals(expected, howIsMood);
    }

    @Test
    public void aCycleSnapCanHaveNoMoodData() {
        String expected = "No data";

        isMoodAvailable = false;
        howIsMood = MoodCalculation.calculateMood(isMoodAvailable, moodAverage);

        Assert.assertEquals(expected, howIsMood);
    }
}
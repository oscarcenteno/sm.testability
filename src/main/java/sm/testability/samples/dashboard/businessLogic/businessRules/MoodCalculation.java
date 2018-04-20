package sm.testability.samples.dashboard.businessLogic.businessRules;

import java.text.DecimalFormat;

public class MoodCalculation {

    public static String calculateMood(boolean isMoodAvailable, double moodAverage) {
        String howIsMood;
        if (isMoodAvailable) {
            DecimalFormat formatter = new DecimalFormat("#.00");
            howIsMood = formatter.format(moodAverage);
            return howIsMood;
        } else {
            howIsMood = "No data";
            return howIsMood;
        }
    }
}

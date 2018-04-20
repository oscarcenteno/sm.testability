package sm.testability.samples.dashboard.businessLogic.businessRules;

import java.text.DecimalFormat;

public class WasteMeasureCalculation {
    public static String calculateWaste(double teamCapacity, double wasteDays, boolean isWasteAvailable) {

        String wastePercentString;
        if (isWasteAvailable) {
            double wastePercent = (wasteDays / teamCapacity);
            wastePercent = wastePercent * 100;

            DecimalFormat formatter = new DecimalFormat("0.0");
            wastePercentString = formatter.format(wastePercent);
            return wastePercentString + "%";

        } else {
            wastePercentString = "No data";
            return wastePercentString;
        }
    }
}

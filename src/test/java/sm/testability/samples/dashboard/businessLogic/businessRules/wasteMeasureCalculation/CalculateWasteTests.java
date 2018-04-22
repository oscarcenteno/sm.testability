package sm.testability.samples.dashboard.businessLogic.businessRules.wasteMeasureCalculation;

import org.junit.*;
import sm.testability.samples.dashboard.businessLogic.businessRules.WasteMeasureCalculation;

public class CalculateWasteTests {

    String expected;
    double teamCapacity;
    double wasteDays;
    boolean isWasteAvailable;
    String wastePercentage;

    @Test
    public void shouldShowThePercentage() {
        expected = "5.0%";

        teamCapacity = 100;
        wasteDays = 5;
        isWasteAvailable = true;
        wastePercentage = WasteMeasureCalculation.calculateWaste(teamCapacity, wasteDays, isWasteAvailable);

        Assert.assertEquals(expected, wastePercentage);
    }

    @Test
    public void shouldShowOnlyOneRoundedDecimal() {
        expected = "5.7%";

        teamCapacity = 99;
        wasteDays = 5.67;
        isWasteAvailable = true;
        wastePercentage = WasteMeasureCalculation.calculateWaste(teamCapacity, wasteDays, isWasteAvailable);

        Assert.assertEquals(expected, wastePercentage);
    }

    @Test
    public void  shouldAllowTwoDigitsForTheWastePercentage ()
    {
        expected = "17.8%";

        teamCapacity = 99;
        wasteDays = 17.67;
        isWasteAvailable = true;
        wastePercentage = WasteMeasureCalculation.calculateWaste(teamCapacity, wasteDays, isWasteAvailable);

        Assert.assertEquals(expected, wastePercentage);
    }

    @Test
    public void shouldAllowZeroWaste() {
        expected = "0.0%";

        teamCapacity = 99;
        wasteDays = 0;
        isWasteAvailable = true;

        wastePercentage = WasteMeasureCalculation.calculateWaste(teamCapacity, wasteDays, isWasteAvailable);

        Assert.assertEquals(expected, wastePercentage);
    }

    @Test
    public void aCycleSnapMayHaveNoWasteData() {
        expected = "No data";

        teamCapacity = 0;
        wasteDays = 0;
        isWasteAvailable = false;
        wastePercentage = WasteMeasureCalculation.calculateWaste(teamCapacity, wasteDays, isWasteAvailable);

        Assert.assertEquals(expected, wastePercentage);
    }
}
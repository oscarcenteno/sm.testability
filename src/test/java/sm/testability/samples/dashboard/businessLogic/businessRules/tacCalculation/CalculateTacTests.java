package sm.testability.samples.dashboard.businessLogic.businessRules.tacCalculation;

import org.junit.Assert;
import org.junit.Test;
import sm.testability.samples.dashboard.businessLogic.businessRules.TacCalculation;

public class CalculateTacTests {

    int targetedPoints;
    int achievedPoints;
    String actual;

    @Test
    public void whenTargetedAPointsIsZero(){
        String expected = "No Data";

        targetedPoints = 0;
        achievedPoints = 0;
        actual = TacCalculation.calculateTac(targetedPoints, achievedPoints);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void whenTargetedAPointsIsNotZero(){
        String expected = "90%";

        targetedPoints = 60;
        achievedPoints = 54;
        actual = TacCalculation.calculateTac(targetedPoints, achievedPoints);

        Assert.assertEquals(expected, actual);
    }
}
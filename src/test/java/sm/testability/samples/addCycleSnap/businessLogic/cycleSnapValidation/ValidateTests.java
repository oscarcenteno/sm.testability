package sm.testability.samples.addCycleSnap.businessLogic.cycleSnapValidation;

import org.junit.Before;
import org.junit.Test;
import sm.testability.samples.addCycleSnap.TestInputs;
import sm.testability.samples.addCycleSnap.businessLogic.inputs.CycleSnap;
import sm.testability.samples.addCycleSnap.businessLogic.CycleSnapValidation;

import java.text.ParseException;
import java.util.List;

public class ValidateTests  {
    CycleSnap newCycle;
    List<CycleSnap> existingCycleSnaps;

    @Before
    public void initialize() throws ParseException {
        newCycle = TestInputs.getValidCycleSnap();
        existingCycleSnaps = TestInputs.getExistingCycles();
    }

    @Test
    public void allFieldsAreValid() throws IllegalArgumentException, ParseException {
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);;
    }

    @Test(expected = IllegalArgumentException.class)
    public void projectIdIsRequired() throws IllegalArgumentException, ParseException {
        newCycle.setProjectId (0);
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);;
    }

    @Test(expected = IllegalArgumentException.class)
    public void projectIdShouldBeGreaterThanZero() throws ParseException {
        newCycle.setProjectId( -1);
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cycleNameIsRequired() throws ParseException {
        newCycle.setCycleSnapName ("");
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);;
    }

    @Test
    public void cycleNameCanBeAnyText() throws ParseException {
        newCycle.setCycleSnapName ("Drop 1");
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);;
    }

    @Test(expected = IllegalArgumentException.class)
    public void cycleNameHasAMaxOf200Characters() throws ParseException {
        newCycle.setCycleSnapName ( "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901");
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test(expected = IllegalArgumentException.class)
    public void startDateIsRequired() throws ParseException {
        newCycle.setStartDate( "");
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test(expected = IllegalArgumentException.class)
    public void endDateIsRequired() throws ParseException {
        newCycle.setEndDate ( "");
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test(expected = IllegalArgumentException.class)
    public void startDateShouldPrecedeEndDate() throws ParseException {
        newCycle.setEndDate ("2016-11-14");
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test
    public void zeroPointsAreValidForTargetedPoints() throws ParseException {
        newCycle.setTargetedPoints (0);
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test
    public void zeroPointsAreValidForAchievedPoints() throws ParseException {
        newCycle.setAchievedPoints ( 0);
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test(expected = IllegalArgumentException.class)
    public void minNumberForTargetedPointsIs0() throws ParseException {
        newCycle.setTargetedPoints (-1);
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test(expected = IllegalArgumentException.class)
    public void minNumberForAchievedPointsIs0() throws ParseException {
        newCycle.setAchievedPoints ( -1);
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test(expected = IllegalArgumentException.class)
    public void maxNumberForTargetedPointsIs10000() throws ParseException {
        newCycle.setTargetedPoints ( 10001);
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test(expected = IllegalArgumentException.class)
    public void maxNumberForAchievedPointsIs10000() throws ParseException {
        newCycle.setAchievedPoints ( 10001);
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newCycleDatesShouldntOverlapWithExistingOnesWhenStartDateOverlapsAnExistingCycle() throws ParseException {
        newCycle.setStartDate ("2016-11-12");
        newCycle.setEndDate ( "2016-11-21");
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newCycleDatesShouldntOverlapWithExistingOnesWhenEndDateOverlapsAnExistingCycle() throws ParseException {
        newCycle.setStartDate ("2016-09-21");
        newCycle.setEndDate ( "2016-10-05");
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newCycleDatesShouldntOverlapWithExistingOnesWhenRangeContainsanExistingCycle() throws ParseException {
        newCycle.setStartDate ("2016-10-02");
        newCycle.setEndDate ( "2016-10-25");
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test
    public void TwoDecimalsforMoodPointsAreValidforMoodColumn() throws ParseException {
        newCycle.setMoodAverage (1.56);
        newCycle.setIsMoodAvailable (true);
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);;
    }

    @Test
    public void moodPointsAreNotRequiredIfMoodDataIsNotAvailableFoMoodColumn() throws ParseException {
        newCycle.setIsMoodAvailable(false);
        newCycle.setMoodAverage ( 0);
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test(expected = IllegalArgumentException.class)
    public void minNumberForMoodPointsIs1ForMoodColumn() throws ParseException {
        newCycle.setMoodAverage ( 0.99);
        newCycle.setIsMoodAvailable(true);
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test(expected = IllegalArgumentException.class)
    public void MoodPointsMaxIs3ForMoodColumn() throws ParseException {
        newCycle.setMoodAverage ( 3.01);
        newCycle.setIsMoodAvailable(true);
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test
    public void twoDecimalsForWasteDaysAreValid() throws ParseException {
        newCycle.setIsWasteAvailable ( true);
        newCycle.setWasteDays (2.59);
        newCycle.setTeamCapacity ( 100.00);
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test
    public void twoDecimalsForTeamCapacityAreValid() throws ParseException {
        newCycle.setIsWasteAvailable ( true);
        newCycle.setWasteDays (5.00);
        newCycle.setTeamCapacity ( 95.89);
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test
    public void teamCapacityAndWasteDaysAreNotRequiredIfWasteDataIsNotAvailable() throws ParseException {
        newCycle.setIsWasteAvailable ( false);
        newCycle.setWasteDays (0);
        newCycle.setTeamCapacity ( 0);
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test(expected = IllegalArgumentException.class)
    public void teamCapacityMinIs1() throws ParseException {
        newCycle.setIsWasteAvailable ( true);
        newCycle.setTeamCapacity ( 0.99);
        newCycle.setWasteDays (5.00);
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test
    public void TeamCapacityOf1IsValid() throws ParseException {
        newCycle.setIsWasteAvailable ( true);
        newCycle.setTeamCapacity ( 1);
        newCycle.setWasteDays (1);
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TeamCapacityMaxIs10000() throws ParseException{
        newCycle.setIsWasteAvailable ( true);
        newCycle.setTeamCapacity ( 10000.01);
        newCycle.setWasteDays (5.00);
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test
    public void teamCapacityOf10000IsValid() throws ParseException{
        newCycle.setIsWasteAvailable ( true);
        newCycle.setTeamCapacity ( 10000);
        newCycle.setWasteDays (5.00);
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test(expected = IllegalArgumentException.class)
    public void wasteDaysMinIs0() throws ParseException{
        newCycle.setIsWasteAvailable ( true);
        newCycle.setTeamCapacity ( 100.00);
        newCycle.setWasteDays (-0.01);
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test
    public void WasteDaysOf0IsValid() throws ParseException{
        newCycle.setIsWasteAvailable (true);
        newCycle.setTeamCapacity (100.00);
        newCycle.setWasteDays (0);
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test(expected = IllegalArgumentException.class)
    public void WasteDaysMaxIsTheTeamsCapacity() throws ParseException{
        newCycle.setIsWasteAvailable ( true);
        newCycle.setTeamCapacity (100);
        newCycle.setWasteDays (100.01);
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);
    }

    @Test
    public void WasteDaysEqualToTheTeamsCapacityIsValid() throws ParseException {
        newCycle.setIsWasteAvailable ( true);
        newCycle.setTeamCapacity (100);
        newCycle.setWasteDays (100);
        CycleSnapValidation.validate(newCycle, existingCycleSnaps);;
    }
}
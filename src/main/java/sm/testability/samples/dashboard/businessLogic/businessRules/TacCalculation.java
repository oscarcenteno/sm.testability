package sm.testability.samples.dashboard.businessLogic.businessRules;


public class TacCalculation {

   public  static String calculateTac(int targetedPoints, int achievedPoints){

    if (targetedPoints == 0 || achievedPoints==0) {
        return "No Data";
    } else {
        int tac = (achievedPoints * 100 / targetedPoints);
        return tac + "%";
    }
}
}

package basic;

public class SIPCalculater{

    public static void main(String[] args) {
        double monthlyInvestment = 10000;
        double annualReturnRate = 120;
        int periodYears = 100;
        double[] results = calculateSip(monthlyInvestment, annualReturnRate, periodYears);
        double principalAmount = results[0];
        double estimatedReturns = results[1];
        double totalAmount = results[2];
        System.out.println("Principal Amount: " + principalAmount);
        System.out.println("Estimated Returns: " + estimatedReturns);
        System.out.println("Total Amount: " + totalAmount);
    }
    public static double[] calculateSip(double monthlyInvestment, double annualReturnRate, int periodYears) {
        double monthlyReturnRate = (annualReturnRate / 100) / 12;
        int totalMonths = periodYears * 12;
        double principalAmount = 0;
        double estimatedReturns = 0;
        for (int month = 1; month <= totalMonths; month++) {
            principalAmount += monthlyInvestment;
            estimatedReturns += (principalAmount + estimatedReturns) * monthlyReturnRate;
        }
        double totalAmount = principalAmount + estimatedReturns;
        return new double[] { principalAmount, estimatedReturns, totalAmount };
    }
}

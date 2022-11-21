public class Business extends Client{
    public static double Overpayment(double bA, double pPY, double pP) {
        double debt = 0.0;

        while (bA > pPY) {
            bA = bA - pPY + debt;
            debt = debt + bA * pP;
        }

        return debt;
    }
}

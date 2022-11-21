public class Human extends Client{
    public double Overpayment(double bA, double pPY, double pP) {
        double debt = 0.0;

        while (bA > 0){
            debt = debt + bA*pP;
            bA = bA*(1.0 + pP) - pPY;
        }

        return debt;
    }
}

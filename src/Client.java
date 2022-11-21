abstract public class Client {
    private double bA;
    private double pPY;
    private double pP;

    public double getBankAccount() {
        return bA;
    }

    public void setBankAccount(double bA){
        this.bA = bA;
    }

    public double getPaymentPerYear() {
        return pPY*12;
    }

    public void setPaymentPerYear(double pPY){
        this.pPY = pPY;
    }

    public double getPaymentPercentage() {
        return pP*0.01;
    }

    public void setPaymentPercentage(double pP){
        this.pP = pP;
    }
}

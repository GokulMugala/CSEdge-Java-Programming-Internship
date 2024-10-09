public class Bill {
    private String patientId;
    private double amount;

    public Bill(String patientId, double amount) {
        this.patientId = patientId;
        this.amount = amount;
    }

    // Getters and setters
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "patientId='" + patientId + '\'' +
                ", amount=" + amount +
                '}';
    }
}

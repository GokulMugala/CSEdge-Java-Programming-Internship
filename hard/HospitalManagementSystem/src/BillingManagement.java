import java.util.HashMap;
import java.util.Map;

public class BillingManagement {
    private final Map<String, Bill> bills;

    public BillingManagement() {
        bills = new HashMap<>();
    }

    public void generateBill(Bill bill) {
        bills.put(bill.getPatientId(), bill);
    }

    public Bill getBill(String patientId) {
        return bills.get(patientId);
    }

    public void displayAllBills() {
        for (Bill bill : bills.values()) {
            System.out.println(bill);
        }
    }
}

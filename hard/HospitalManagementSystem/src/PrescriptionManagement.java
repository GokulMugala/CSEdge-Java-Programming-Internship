import java.util.HashMap;
import java.util.Map;

public class PrescriptionManagement {
    private final Map<String, Prescription> prescriptions;

    public PrescriptionManagement() {
        prescriptions = new HashMap<>();
    }

    public void addPrescription(Prescription prescription) {
        prescriptions.put(prescription.getPatientId(), prescription);
    }

    public Prescription getPrescription(String patientId) {
        return prescriptions.get(patientId);
    }

    public void displayAllPrescriptions() {
        for (Prescription prescription : prescriptions.values()) {
            System.out.println(prescription);
        }
    }
}

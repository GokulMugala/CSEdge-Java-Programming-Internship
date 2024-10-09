import java.util.ArrayList;
import java.util.List;

public class PatientManagement {
    private final List<Patient> patients;

    public PatientManagement() {
        patients = new ArrayList<>();
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void removePatient(String id) {
        patients.removeIf(patient -> patient.getId().equals(id));
    }

    public Patient getPatient(String id) {
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) {
                return patient;
            }
        }
        return null;
    }

    public void displayAllPatients() {
        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }
}

public class Prescription {
    private String patientId;
    private String medication;
    private String dosage;

    public Prescription(String patientId, String medication, String dosage) {
        this.patientId = patientId;
        this.medication = medication;
        this.dosage = dosage;
    }

    // Getters and setters
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "patientId='" + patientId + '\'' +
                ", medication='" + medication + '\'' +
                ", dosage='" + dosage + '\'' +
                '}';
    }
}

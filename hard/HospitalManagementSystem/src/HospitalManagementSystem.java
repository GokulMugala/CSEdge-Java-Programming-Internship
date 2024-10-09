public class HospitalManagementSystem {
    public static void main(String[] args) {
        PatientManagement patientManagement = new PatientManagement();
        AppointmentManagement appointmentManagement = new AppointmentManagement();
        PrescriptionManagement prescriptionManagement = new PrescriptionManagement();
        BillingManagement billingManagement = new BillingManagement();
        InventoryManagement inventoryManagement = new InventoryManagement();

        // Add sample data and test the system
        Patient patient1 = new Patient("1", "John Doe", 30, "Flu");
        patientManagement.addPatient(patient1);

        Appointment appointment1 = new Appointment("1", "Dr. Smith", "2024-10-07");
        appointmentManagement.scheduleAppointment(appointment1);

        Prescription prescription1 = new Prescription("1", "Paracetamol", "500mg");
        prescriptionManagement.addPrescription(prescription1);

        Bill bill1 = new Bill("1", 150.0);
        billingManagement.generateBill(bill1);

        InventoryItem item1 = new InventoryItem("1", "Syringe", 100);
        inventoryManagement.addItem(item1);

        // Display all data
        patientManagement.displayAllPatients();
        appointmentManagement.displayAllAppointments();
        prescriptionManagement.displayAllPrescriptions();
        billingManagement.displayAllBills();
        inventoryManagement.displayAllItems();
    }
}

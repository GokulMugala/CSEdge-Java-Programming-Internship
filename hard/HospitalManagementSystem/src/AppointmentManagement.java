import java.util.HashMap;
import java.util.Map;

public class AppointmentManagement {
    private final Map<String, Appointment> appointments;

    public AppointmentManagement() {
        appointments = new HashMap<>();
    }

    public void scheduleAppointment(Appointment appointment) {
        appointments.put(appointment.getPatientId(), appointment);
    }

    public void cancelAppointment(String patientId) {
        appointments.remove(patientId);
    }

    public Appointment getAppointment(String patientId) {
        return appointments.get(patientId);
    }

    public void displayAllAppointments() {
        for (Appointment appointment : appointments.values()) {
            System.out.println(appointment);
        }
    }
}

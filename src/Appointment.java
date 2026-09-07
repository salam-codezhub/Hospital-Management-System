import java.io.Serializable;

public class Appointment implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int id;
    private final int patientId;
    private final int doctorId;
    private final String date;
    private final String time;

    public Appointment(int id, int patientId, int doctorId, String date, String time) {
        if (id <= 0 || patientId <= 0 || doctorId <= 0)
            throw new IllegalArgumentException("IDs must be positive.");
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
    }

    public int getId() { return id; }
    public int getPatientId() { return patientId; }
    public int getDoctorId() { return doctorId; }
    public String getDate() { return date; }
    public String getTime() { return time; }

    public void displayInfo(Hospital hospital) {
        String patient = hospital.findPatient(patientId).map(Patient::getName).orElse("Unknown");
        String doctor = hospital.findDoctor(doctorId).map(Doctor::getName).orElse("Unknown");
        System.out.printf("ID: %-4d | Patient: %-18s | Doctor: %-18s | %s %s%n",
                id, patient, doctor, date, time);
    }
}

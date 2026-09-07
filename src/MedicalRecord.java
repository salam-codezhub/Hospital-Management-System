import java.io.Serializable;

public class MedicalRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int recordId;
    private final int patientId;
    private final int doctorId;
    private final String diagnosis;
    private final String prescription;

    public MedicalRecord(int recordId, int patientId, int doctorId,
                         String diagnosis, String prescription) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
    }

    public int getRecordId() { return recordId; }
    public int getPatientId() { return patientId; }

    public void displayInfo(Hospital hospital) {
        String patient = hospital.findPatient(patientId).map(Patient::getName).orElse("Unknown");
        String doctor = hospital.findDoctor(doctorId).map(Doctor::getName).orElse("Unknown");
        System.out.printf("Record #%d | Patient: %s | Doctor: %s%nDiagnosis: %s%nPrescription: %s%n",
                recordId, patient, doctor, diagnosis, prescription);
    }
}

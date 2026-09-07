import java.io.Serializable;

public class Bill implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int billId;
    private final int patientId;
    private final double consultationFee;
    private final double medicineFee;
    private boolean paid;

    public Bill(int billId, int patientId, double consultationFee, double medicineFee) {
        if (billId <= 0 || patientId <= 0 || consultationFee < 0 || medicineFee < 0)
            throw new IllegalArgumentException("Invalid bill details.");
        this.billId = billId;
        this.patientId = patientId;
        this.consultationFee = consultationFee;
        this.medicineFee = medicineFee;
    }

    public int getBillId() { return billId; }
    public int getPatientId() { return patientId; }
    public double getTotal() { return consultationFee + medicineFee; }
    public boolean isPaid() { return paid; }
    public void markPaid() { paid = true; }

    public void displayInfo(Hospital hospital) {
        String patient = hospital.findPatient(patientId).map(Patient::getName).orElse("Unknown");
        System.out.printf("Bill #%d | Patient: %-18s | Total: %.2f | Status: %s%n",
                billId, patient, getTotal(), paid ? "PAID" : "PENDING");
    }
}

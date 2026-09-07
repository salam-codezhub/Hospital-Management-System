import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public class Hospital {
    private final ArrayList<Patient> patients;
    private final ArrayList<Doctor> doctors;
    private final ArrayList<Appointment> appointments;
    private final ArrayList<MedicalRecord> records;
    private final ArrayList<Bill> bills;

    public Hospital() {
        patients = FileManager.load("data/patients.dat");
        doctors = FileManager.load("data/doctors.dat");
        appointments = FileManager.load("data/appointments.dat");
        records = FileManager.load("data/records.dat");
        bills = FileManager.load("data/bills.dat");
    }

    public Optional<Patient> findPatient(int id) {
        return patients.stream().filter(p -> p.getId() == id).findFirst();
    }

    public Optional<Doctor> findDoctor(int id) {
        return doctors.stream().filter(d -> d.getId() == id).findFirst();
    }

    private boolean patientIdExists(int id) { return findPatient(id).isPresent(); }
    private boolean doctorIdExists(int id) { return findDoctor(id).isPresent(); }

    public void addPatient(Patient p) throws HospitalException {
        if (patientIdExists(p.getId())) throw new HospitalException("Patient ID already exists.");
        patients.add(p);
        saveAll();
    }

    public void addDoctor(Doctor d) throws HospitalException {
        if (doctorIdExists(d.getId())) throw new HospitalException("Doctor ID already exists.");
        doctors.add(d);
        saveAll();
    }

    public void addAppointment(Appointment a) throws HospitalException {
        if (!patientIdExists(a.getPatientId())) throw new HospitalException("Patient not found.");
        if (!doctorIdExists(a.getDoctorId())) throw new HospitalException("Doctor not found.");

        boolean conflict = appointments.stream().anyMatch(x ->
                x.getDoctorId() == a.getDoctorId() &&
                x.getDate().equalsIgnoreCase(a.getDate()) &&
                x.getTime().equalsIgnoreCase(a.getTime()));

        if (conflict) throw new HospitalException("Doctor already has an appointment at that time.");
        appointments.add(a);
        saveAll();
    }

    public void addRecord(MedicalRecord r) throws HospitalException {
        if (!patientIdExists(r.getPatientId())) throw new HospitalException("Patient not found.");
        records.add(r);
        saveAll();
    }

    public void addBill(Bill b) throws HospitalException {
        if (!patientIdExists(b.getPatientId())) throw new HospitalException("Patient not found.");
        bills.add(b);
        saveAll();
    }

    public void markBillPaid(int id) throws HospitalException {
        for (Bill b : bills) {
            if (b.getBillId() == id) {
                b.markPaid();
                saveAll();
                return;
            }
        }
        throw new HospitalException("Bill not found.");
    }

    public void showPatients() {
        System.out.println("\n--- PATIENTS ---");
        patients.stream().sorted(Comparator.comparingInt(Patient::getId))
                .forEach(Patient::displayInfo);
        if (patients.isEmpty()) System.out.println("No patients found.");
    }

    public void showDoctors() {
        System.out.println("\n--- DOCTORS ---");
        doctors.stream().sorted(Comparator.comparingInt(Doctor::getId))
                .forEach(Doctor::displayInfo);
        if (doctors.isEmpty()) System.out.println("No doctors found.");
    }

    public void showAppointments() {
        System.out.println("\n--- APPOINTMENTS ---");
        appointments.forEach(a -> a.displayInfo(this));
        if (appointments.isEmpty()) System.out.println("No appointments found.");
    }

    public void showRecords() {
        System.out.println("\n--- MEDICAL RECORDS ---");
        records.forEach(r -> r.displayInfo(this));
        if (records.isEmpty()) System.out.println("No medical records found.");
    }

    public void showBills() {
        System.out.println("\n--- BILLS ---");
        bills.forEach(b -> b.displayInfo(this));
        if (bills.isEmpty()) System.out.println("No bills found.");
    }

    public void searchPatient(String keyword) {
        System.out.println("\n--- SEARCH RESULTS ---");
        patients.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase())
                         || String.valueOf(p.getId()).equals(keyword))
                .forEach(Patient::displayInfo);
    }

    private void saveAll() {
        FileManager.save(patients, "data/patients.dat");
        FileManager.save(doctors, "data/doctors.dat");
        FileManager.save(appointments, "data/appointments.dat");
        FileManager.save(records, "data/records.dat");
        FileManager.save(bills, "data/bills.dat");
    }
}

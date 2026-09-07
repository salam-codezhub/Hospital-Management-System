import java.util.Scanner;

public class Main {
    private static void printMenu() {
        System.out.println("\n==========================================");
        System.out.println("        HOSPITAL MANAGEMENT SYSTEM");
        System.out.println("==========================================");
        System.out.println("1. Add Patient");
        System.out.println("2. Add Doctor");
        System.out.println("3. Book Appointment");
        System.out.println("4. Add Medical Record");
        System.out.println("5. Create Bill");
        System.out.println("6. Mark Bill as Paid");
        System.out.println("7. Show Patients");
        System.out.println("8. Show Doctors");
        System.out.println("9. Show Appointments");
        System.out.println("10. Show Medical Records");
        System.out.println("11. Show Bills");
        System.out.println("12. Search Patient");
        System.out.println("0. Exit");
        System.out.println("==========================================");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputValidator input = new InputValidator(scanner);
        Hospital hospital = new Hospital();

        while (true) {
            printMenu();
            int choice = input.readInt("Enter choice: ");

            try {
                switch (choice) {
                    case 1 -> {
                        int id = input.readInt("Patient ID: ");
                        String name = input.readText("Name: ");
                        String phone = input.readText("Phone: ");
                        int age = input.readInt("Age: ");
                        String disease = input.readText("Disease: ");
                        hospital.addPatient(new Patient(id, name, phone, age, disease));
                        System.out.println("Patient added successfully.");
                    }
                    case 2 -> {
                        int id = input.readInt("Doctor ID: ");
                        String name = input.readText("Name: ");
                        String phone = input.readText("Phone: ");
                        String spec = input.readText("Specialization: ");
                        hospital.addDoctor(new Doctor(id, name, phone, spec));
                        System.out.println("Doctor added successfully.");
                    }
                    case 3 -> {
                        int id = input.readInt("Appointment ID: ");
                        int patientId = input.readInt("Patient ID: ");
                        int doctorId = input.readInt("Doctor ID: ");
                        String date = input.readText("Date (DD-MM-YYYY): ");
                        String time = input.readText("Time (e.g. 10:30 AM): ");
                        hospital.addAppointment(new Appointment(id, patientId, doctorId, date, time));
                        System.out.println("Appointment booked successfully.");
                    }
                    case 4 -> {
                        int id = input.readInt("Record ID: ");
                        int patientId = input.readInt("Patient ID: ");
                        int doctorId = input.readInt("Doctor ID: ");
                        String diagnosis = input.readText("Diagnosis: ");
                        String prescription = input.readText("Prescription: ");
                        hospital.addRecord(new MedicalRecord(id, patientId, doctorId, diagnosis, prescription));
                        System.out.println("Medical record added.");
                    }
                    case 5 -> {
                        int id = input.readInt("Bill ID: ");
                        int patientId = input.readInt("Patient ID: ");
                        double consultation = input.readDouble("Consultation fee: ");
                        double medicine = input.readDouble("Medicine fee: ");
                        hospital.addBill(new Bill(id, patientId, consultation, medicine));
                        System.out.println("Bill created.");
                    }
                    case 6 -> {
                        int id = input.readInt("Bill ID: ");
                        hospital.markBillPaid(id);
                        System.out.println("Bill marked as paid.");
                    }
                    case 7 -> hospital.showPatients();
                    case 8 -> hospital.showDoctors();
                    case 9 -> hospital.showAppointments();
                    case 10 -> hospital.showRecords();
                    case 11 -> hospital.showBills();
                    case 12 -> hospital.searchPatient(input.readText("Enter patient ID or name: "));
                    case 0 -> {
                        System.out.println("Thank you for using the system.");
                        scanner.close();
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (HospitalException | IllegalArgumentException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
    }
}

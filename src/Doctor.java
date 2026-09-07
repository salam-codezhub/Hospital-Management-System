public class Doctor extends Person {
    private static final long serialVersionUID = 1L;
    private String specialization;

    public Doctor(int id, String name, String phone, String specialization) {
        super(id, name, phone);
        if (specialization == null || specialization.isBlank())
            throw new IllegalArgumentException("Specialization cannot be empty.");
        this.specialization = specialization.trim();
    }

    public String getSpecialization() { return specialization; }

    @Override public String getRole() { return "Doctor"; }

    @Override public void displayInfo() {
        System.out.printf("ID: %-4d | %-20s | Phone: %-12s | Specialization: %s%n",
                getId(), getName(), getPhone(), specialization);
    }
}

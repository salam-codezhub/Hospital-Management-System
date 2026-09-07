public class Patient extends Person {
    private static final long serialVersionUID = 1L;
    private int age;
    private String disease;

    public Patient(int id, String name, String phone, int age, String disease) {
        super(id, name, phone);
        if (age < 0 || age > 120) throw new IllegalArgumentException("Age must be 0-120.");
        this.age = age;
        this.disease = disease == null ? "Not specified" : disease.trim();
    }

    public int getAge() { return age; }
    public String getDisease() { return disease; }

    @Override public String getRole() { return "Patient"; }

    @Override public void displayInfo() {
        System.out.printf("ID: %-4d | %-20s | Phone: %-12s | Age: %-3d | Disease: %s%n",
                getId(), getName(), getPhone(), age, disease);
    }
}

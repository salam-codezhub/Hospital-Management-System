import java.io.Serializable;

public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int id;
    private String name;
    private String phone;

    protected Person(int id, String name, String phone) {
        if (id <= 0 || name == null || name.isBlank()) {
            throw new IllegalArgumentException("Invalid person details.");
        }
        this.id = id;
        this.name = name.trim();
        this.phone = phone == null ? "" : phone.trim();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }

    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be empty.");
        this.name = name.trim();
    }

    public void setPhone(String phone) { this.phone = phone == null ? "" : phone.trim(); }

    public abstract String getRole();
    public abstract void displayInfo();
}

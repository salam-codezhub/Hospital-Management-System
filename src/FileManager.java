import java.io.*;
import java.util.ArrayList;

public class FileManager {
    private FileManager() {}

    public static <T> void save(ArrayList<T> data, String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(data);
        } catch (IOException e) {
            System.out.println("Warning: Could not save " + fileName + ": " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> load(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Warning: Could not load " + fileName + ". Starting with empty data.");
            return new ArrayList<>();
        }
    }
}

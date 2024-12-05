import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Admin {
    private final String adminEmail = "admin@example.com";
    private final String adminPassword = "admin123";

    public boolean login(String email, String password) {
        return email.equals(adminEmail) && password.equals(adminPassword);
    }

    public void viewAllUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            System.out.println("All Registered Users:");
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String fullName = parts[0];
                String email = parts[1];
                String encryptedPassword = parts[2];
                System.out.println("Name: " + fullName + ", Email: " + email + ", Password: " + Encryption.decrypt(encryptedPassword));
            }
        } catch (IOException e) {
            System.out.println("Error reading user data.");
        }
    }
}

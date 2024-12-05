import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.Scanner;
public class Game extends Website{
    @Override
    public void login(String email, String password) {
        if (authenticateUser(email, password)) {
            System.out.println("Login successful for Website!");
            userMenu(email);
        } else {
            System.out.println("Invalid credentials for Website.");
        }
    }

    @Override
    public void editPassword(String email) {
        System.out.println("Editing password for Website.");
        updatePassword(email);
    }

    @Override
    public void deletePassword(String email) {
        System.out.println("Deleting password for Website.");
        deleteUser(email);
    }

    private boolean authenticateUser(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[1].equals(email) && Encryption.decrypt(parts[2]).equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error accessing user data.");
        }
        return false;
    }

    private void userMenu(String email) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Edit Password");
        System.out.println("2. Delete Password");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            editPassword(email);
        } else if (choice == 2) {
            deletePassword(email);
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private void updatePassword(String email) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        StringBuilder updatedData = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[1].equals(email)) {
                    updatedData.append(parts[0]).append(",").append(email).append(",").append(Encryption.encrypt(newPassword)).append("\n");
                } else {
                    updatedData.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading user data.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
            writer.write(updatedData.toString());
            System.out.println("Password updated successfully!");
        } catch (IOException e) {
            System.out.println("Error updating user data.");
        }
    }

    private void deleteUser(String email) {
        StringBuilder updatedData = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (!parts[1].equals(email)) {
                    updatedData.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading user data.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
            writer.write(updatedData.toString());
            System.out.println("User deleted successfully!");
        } catch (IOException e) {
            System.out.println("Error updating user data.");
        }
    }


}

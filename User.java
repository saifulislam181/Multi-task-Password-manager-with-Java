// User class

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.Scanner;
class User {
    String fullName;
    String email;
    String password;

    public User(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = Encryption.encrypt(password); // Encrypt password before saving
    }

    public void register() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            writer.write(fullName + "," + email + "," + password);
            writer.newLine();
            System.out.println("User registered successfully!");
        } catch (IOException e) {
            System.out.println("Error saving user data.");
        }
    }
}
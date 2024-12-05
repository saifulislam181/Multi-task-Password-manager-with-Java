import java.io.*;
import java.util.Scanner;
public class PasswordManager {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Admin admin = new Admin();
        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Website");
            System.out.println("2. Desktop Application");
            System.out.println("3. Game");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 4) {
                System.out.println("Exiting...");
                break;
            }

            System.out.println("1. Login");
            System.out.println("2. Register");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (option == 1) {
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                if (admin.login(email, password)) {
                    System.out.println("Welcome Admin!");
                    admin.viewAllUsers();
                } else {
                    UseCase useCase = getUseCase(choice);
                    if (useCase != null) {
                        useCase.login(email, password);
                    }
                }
            } else if (option == 2) {
                System.out.print("Enter full name: ");
                String fullName = scanner.nextLine();
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                User user = new User(fullName, email, password);
                user.register();
            } else {
                System.out.println("Invalid option!");
            }
        }
    }

    private static UseCase getUseCase(int choice) {
        return switch (choice) {
            case 1 -> new Website();
            case 2 -> new DesktopApplication();
            case 3 -> new Game();
            default -> null;
        };
    }
}
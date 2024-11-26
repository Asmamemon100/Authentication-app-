import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

public class AuthenticationSystem {
    private static List<User> users = new ArrayList<>();
    private static boolean isLoggedIn = false;
    private static String loggedInUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Authentication System ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Logout");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> registerUser(scanner);
                case 2 -> loginUser(scanner);
                case 3 -> logoutUser();
                case 4 -> {
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void registerUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (isUsernameTaken(username)) {
            System.out.println("Username is already taken. Please choose another one.");
        } else {
            users.add(new User(username, password));
            System.out.println("Registration successful! You can now log in.");
        }
    }

    private static boolean isUsernameTaken(String username) {
        return users.stream().anyMatch(user -> user.getUsername().equals(username));
    }

    private static void loginUser(Scanner scanner) {
        if (isLoggedIn) {
            System.out.println("You are already logged in as: " + loggedInUser);
            return;
        }

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = users.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (user != null) {
            isLoggedIn = true;
            loggedInUser = username;
            System.out.println("Login successful! Welcome, " + username + ".");
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private static void logoutUser() {
        if (!isLoggedIn) {
            System.out.println("No user is currently logged in.");
        } else {
            System.out.println("User " + loggedInUser + " has logged out.");
            isLoggedIn = false;
            loggedInUser = null;
        }
    }
}

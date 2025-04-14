import java.util.*;
import java.util.List;

public class Admin extends User {
    private static Scanner scanner = new Scanner(System.in);

    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=== Admin Menu ===");
        System.out.println("1. Add User");
        System.out.println("2. Remove User");
        System.out.println("3. List Users");
        System.out.println("0. Logout");
    }

    public void handleOption(String choice, List<User> users) {
        switch (choice) {
            case "1": addUser(users); break;
            case "2": removeUser(users); break;
            case "3": listUsers(users); break;
            default: System.out.println("Invalid choice.");
        }
    }

    private void addUser(List<User> users) {
        System.out.print("Enter role (admin/librarian/reader): ");
        String role = scanner.nextLine().toLowerCase();
        System.out.print("Enter username: ");
        String newUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        String newPassword = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(newUsername)) {
                System.out.println("User already exists.");
                return;
            }
        }

        switch (role) {
            case "admin": users.add(new Admin(newUsername, newPassword)); break;
            case "librarian": users.add(new Librarian(newUsername, newPassword)); break;
            case "reader": users.add(new Reader(newUsername, newPassword)); break;
            default: System.out.println("Invalid role."); return;
        }

        FileHandler.saveUsers(users);
        System.out.println("User added successfully.");
    }

    private void removeUser(List<User> users) {
        System.out.print("Enter username to remove: ");
        String usernameToRemove = scanner.nextLine();

        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getUsername().equals(usernameToRemove)) {
                iterator.remove();
                FileHandler.saveUsers(users);
                System.out.println("User removed.");
                return;
            }
        }

        System.out.println("User not found.");
    }

    private void listUsers(List<User> users) {
        System.out.println("\n--- Registered Users ---");
        for (User user : users) {
            System.out.println(user.getClass().getSimpleName() + " - " + user.getUsername());
        }
    }
}

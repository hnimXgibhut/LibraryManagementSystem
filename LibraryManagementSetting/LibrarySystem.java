import java.util.*;
import java.BufferedReader;
import java.util.List;

public class LibrarySystem {
  private static List<User> users = FileHandler.loadUsers();
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("Welcome to the Library Management System\n");
    User currentUser = null;

    while (currentUser == null) {
      System.out.print("Enter username: ");
      String username = scanner.nextLine();
      System.out.print("Enter password: ");
      String password = scanner.nextLine();

      for (User user : users) {
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
          currentUser = user;
          break;
        }
      }

      if (currentUser == null) {
        System.out.println("Invalid credentials. Try again.\n");
      }
    }

    System.out.println("\nLogin successful. Welcome, " + currentUser.getUsername() + "!");
    boolean running = true;
    while (running) {
      currentUser.displayMenu();
      System.out.print("Select option: ");
      String choice = scanner.nextLine();
      if (choice.equals("0")) {
        System.out.println("Logging out...");
        running = false;
      } else if (currentUser instanceof Admin) {
        ((Admin) currentUser).handleOption(choice, users);
      } else if (currentUser instanceof Librarian) {
        ((Librarian) currentUser).handleOption(choice);
      } else if (currentUser instanceof Reader) {
        ((Reader) currentUser).handleOption(choice);
      }
    }
  }
}


import java.util.*;
import java.time.LocalDate;

public class Reader extends User {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Book> books = FileHandler.loadBooks();

    public Reader(String username, String password) {
        super(username, password);
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=== Reader Menu ===");
        System.out.println("1. Borrow a Book");
        System.out.println("2. Return a Book");
        System.out.println("3. View All Books");
        System.out.println("0. Logout");
    }

    public void handleOption(String choice) {
        switch (choice) {
            case "1":
                borrowBook();
                break;
            case "2":
                returnBook();
                break;
            case "3":
                viewBooks();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void borrowBook() {
        System.out.print("Enter ISBN to borrow: ");
        String isbn = scanner.nextLine();
        for (Book b : books) {
            if (b.getISBN().equals(isbn)) {
                if (b instanceof Ebook) {
                    System.out.println("Ebooks can't be borrowed.");
                    return;
                }
                if (!b.getIsAvailable()) {
                    System.out.println("Book is already borrowed.");
                    return;
                }
                b.setIsAvailable(false);
                String dueDate = LocalDate.now().plusDays(14).toString();
                b.setDueDate(dueDate);
                FileHandler.saveBooks(books);
                FileHandler.logTransaction(super.getUsername(), isbn, "borrow", dueDate);
                System.out.println("Borrowed. Due: " + dueDate);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    private void returnBook() {
        System.out.print("Enter ISBN to return: ");
        String isbn = scanner.nextLine();
        for (Book b : books) {
            if (b.getISBN().equals(isbn)) {
                if (b.getIsAvailable()) {
                    System.out.println("Book wasn’t borrowed.");
                    return;
                }
                b.setIsAvailable(true);
                b.setDueDate("");
                FileHandler.saveBooks(books);
                FileHandler.logTransaction(super.getUsername(), isbn, "return", LocalDate.now().toString());
                System.out.println("Book returned.");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    private void viewBooks() {
        System.out.println("\n--- All Books ---");
        for (Book b : books) {
            System.out.println(b);
        }
    }
}

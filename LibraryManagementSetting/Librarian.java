
import java.util.*;

public class Librarian extends User {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Book> books = FileHandler.loadBooks();

    public Librarian(String username, String password) {
        super(username, password);
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=== Librarian Menu ===");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. List All Available Books");
        System.out.println("0. Logout");
    }

    public void handleOption(String choice) {
        switch (choice) {
            case "1":
                addBook();
                break;
            case "2":
                removeBook();
                break;
            case "3":
                listBooks();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void addBook() {
        System.out.print("Enter type (PrintedBook/Ebook): ");
        String type = scanner.nextLine();

        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        for (Book b : books) {
            if (b.getISBN().equals(isbn)) {
                System.out.println("Book already exists.");
                return;
            }
        }

        if (type.equalsIgnoreCase("PrintedBook")) {
            System.out.print("Enter number of pages: ");
            int pages = Integer.parseInt(scanner.nextLine());
            books.add(new PrintedBook(title, author, genre, isbn, true, "", pages));
        } else if (type.equalsIgnoreCase("Ebook")) {
            System.out.print("Enter file format: ");
            String format = scanner.nextLine();
            books.add(new EBook(title, author, genre, isbn, false, "", format));
        } else {
            System.out.println("Invalid type.");
            return;
        }

        FileHandler.saveBooks(books);
        System.out.println("Book added.");
    }

    private void removeBook() {
        System.out.print("Enter ISBN of book to remove: ");
        String isbn = scanner.nextLine();

        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book b = iterator.next();
            if (b.getISBN().equals(isbn)) {
                iterator.remove();
                FileHandler.saveBooks(books);
                System.out.println("Book removed.");
                return;
            }
        }

        System.out.println("Book not found.");
    }

    private void listBooks() {
        System.out.println("\n--- Available Books ---");
        for (Book b : books) {
            if (b.getIsAvailable()) {
                System.out.println(b);
            }
        }
    }
}

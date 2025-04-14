import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileHandler {
    private static final String USERS_FILE = "D:/users.txt";
    private static final String BOOKS_FILE = "D:/books.txt";

    private static final String TRANSACTIONS_FILE = "D:/transactions.txt";

    // Load users from the users.txt file
    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String role = parts[0];
                String username = parts[1];
                String password = parts[2];
                switch (role.toLowerCase()) {
                    case "admin":
                        users.add(new Admin(username, password));
                        break;
                    case "librarian":
                        users.add(new Librarian(username, password));
                        break;
                    case "reader":
                        users.add(new Reader(username, password));
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
        return users;
    }

    public static void saveUsers(List<User> users) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (User user : users) {
                String role = user.getClass().getSimpleName().toLowerCase(); // e.g. admin, librarian, reader
                bw.write(role + "," + user.getUsername() + "," + user.getPassword());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    // Load books from books.txt file
    public static List<Book> loadBooks() {
        ArrayList<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                String title = parts[1];
                String author = parts[2];
                String genre = parts[3];
                String isbn = parts[4];
                boolean isAvailable = Boolean.parseBoolean(parts[5]);
                String dueDate = parts[6];
                if (type.equals("PrintedBook")) {
                    int pages = Integer.parseInt(parts[7]);
                    books.add(new PrintedBook(title, author, genre, isbn, isAvailable, dueDate, pages));
                } else if (type.equals("Ebook")) {
                    String fileFormat = parts[7];
                    books.add(new EBook(title, author, genre, isbn, isAvailable, dueDate, fileFormat));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
        return books;
    }

    // Save the list of books back to books.txt file
    public static void saveBooks(List<Book> books) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BOOKS_FILE))) {
            for (Book book : books) {
                bw.write(book.toDataString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    // Log a transaction (borrow/return) in transactions.txt file
    public static void logTransaction(String username, String isbn, String action, String date) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TRANSACTIONS_FILE, true))) {
            bw.write(username + "," + isbn + "," + action + "," + date);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error logging transaction: " + e.getMessage());
        }
    }
}

public abstract class Book {
    private String title;
    private String author;
    private String genre;
    private String ISBN;
    private boolean isAvailable;
    private String dueDate;

    public Book(String title, String author, String genre, String ISBN, boolean isAvailable, String dueDate) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.ISBN = ISBN;
        this.isAvailable =isAvailable;
        this.dueDate = dueDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return dueDate;
    }
    public abstract String toDataString(); // for saving to file

    @Override
    public String toString() {
        return String.format("%s: %s by %s (%s) [ISBN: %s] - %s %s",
                getClass().getSimpleName(),
                title,
                author,
                genre,
                ISBN,
                isAvailable ? "Available" : "Borrowed",
                isAvailable ? "" : "(Due: " + dueDate + ")");
    }
}


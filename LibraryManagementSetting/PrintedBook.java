public class PrintedBook extends Book {
    private int numOfPages;

    public PrintedBook(String title, String author, String genre, String ISBN, boolean isAvailable, String dueDate,
            int numOfPages) {
        super(title, author, genre, ISBN, isAvailable, dueDate);
        this.numOfPages = numOfPages;
    }

    public int getNumPages() {
        return numOfPages;
    }

    public void setNumPages(int numOfPages) {
        this.numOfPages = numOfPages;
    }

    @Override
    public String toDataString() {
        return String.join(",",
                "PrintedBook",
                super.getTitle(),
                super.getAuthor(),
                super.getGenre(),
                super.getISBN(),
                String.valueOf(super.getIsAvailable()),
                super.getDueDate(),
                String.valueOf(numOfPages)
        );
} 
}

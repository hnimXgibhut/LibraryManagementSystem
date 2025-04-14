public class EBook extends Book {
    private String fileformat;

    public EBook(String title, String author, String genre, String ISBN, boolean isAvailable, String dueDate,
            String fileformat) {
        super(title, author, genre, ISBN, isAvailable, dueDate);
        this.fileformat = fileformat;
    }

    public String getFileFormat() {
        return fileformat;
    }

    public void setFileFormat(int fileformat) {
        this.fileformat = fileformat;
    }

    @Override
    public String toDataString() {
        return String.join(",",
                "EBook",
                super.getTitle(),
                super.getAuthor(),
                super.getGenre(),
                super.getISBN(),
                String.valueOf(super.getIsAvailable()),
                super.getDueDate(),
                fileformat);
    }

}

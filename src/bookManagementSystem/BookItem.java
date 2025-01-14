package bookManagementSystem;

import libraryManagement.Rack;

public class BookItem extends Book {
    private String uniqueId;
    private boolean isAvailable;
    private Rack rack;

    public BookItem(String uniqueId, Book book, Rack rack) {
        super(book.getTitle(), book.getAuthor(), book.getPublicationDate(),book.getCategory(), book.getISBN());
        this.uniqueId = uniqueId;
        this.isAvailable = true;
        this.rack = rack;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Rack getRack() {
        return rack;
    }
}



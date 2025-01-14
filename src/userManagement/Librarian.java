package userManagement;

import libraryManagement.Library;

import java.time.LocalDateTime;

public class Librarian extends Account {

    public Librarian(String username, String name, String mobile, String address, String password) {
        super(username, password, name, mobile, address, "Librarian");
    }

    public void performLibrarianActions() {
        System.out.println("Welcome Librarian! You can manage the library.");
        // Add librarian-specific actions here (e.g., menu for adding books, removing books, etc.).
        System.out.println("You can add , remove books");
    }

    public void addBook(String title, String author, LocalDateTime publicationDate, String category, String isbn, Library library) {
        library.addBook(title, author, publicationDate, category, isbn);
        System.out.println("Book added successfully to the library.");
    }

    public void removeBook(String isbn, String uniqueId, Library library) {
        library.removeBook(isbn, uniqueId);}


}

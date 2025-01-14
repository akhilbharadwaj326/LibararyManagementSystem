package libraryManagement;

import bookManagementSystem.Book;
import bookManagementSystem.BookItem;
import userManagement.Patron;

import java.time.LocalDateTime;
import java.util.*;

public class Library {
    private Map<String, Book> books; // Keyed by ISBN
    private Map<String, List<BookItem>> bookItems; // Keyed by ISBN
    private Map<Book, Rack> bookRacks; // Mapping of books to racks
    private Map<Integer, Rack> racks; // Keyed by rack number
    private int nextRackNumber = 1; // Auto-increment for rack numbers

    public Library() {
        books = new HashMap<>();
        bookItems = new HashMap<>();
        bookRacks = new HashMap<>();
        racks = new HashMap<>();
    }

    public void addBook(String title, String author, LocalDateTime publicationDate, String category, String isbn) {
        Book book = books.get(isbn);
        Rack rack;

        if (book == null) {
            book = new Book(title, author, publicationDate, category, isbn);
            books.put(isbn, book);

            rack = new Rack(nextRackNumber++);
            racks.put(rack.getRackNumber(), rack);
            bookRacks.put(book, rack);
            System.out.println("New rack " + rack.getRackNumber() + " created and assigned to book '" + title + "'.");
        } else {
            rack = bookRacks.get(book);
        }

        int count = bookItems.getOrDefault(isbn, new ArrayList<>()).size() + 1;
        String uniqueId = title.substring(0, Math.min(3, title.length())).toUpperCase() + count;

        BookItem bookItem = new BookItem(uniqueId, book, rack);
        rack.addBookItem(bookItem);
        bookItems.computeIfAbsent(isbn, k -> new ArrayList<>()).add(bookItem);

        System.out.println("Book '" + title + "' (Copy ID: " + uniqueId + ") added to rack " + rack.getRackNumber() + ".");
    }

    public void removeBook(String isbn, String uniqueId) {
        List<BookItem> items = bookItems.get(isbn);

        if (items == null || items.isEmpty()) {
            System.out.println("No books found with ISBN: " + isbn);
            return;
        }

        BookItem toRemove = null;
        for (BookItem item : items) {
            if (item.getUniqueId().equals(uniqueId)) {
                toRemove = item;
                break;
            }
        }

        if (toRemove != null) {
            Rack rack = toRemove.getRack();
            rack.removeBookItem(toRemove);
            items.remove(toRemove);
            System.out.println("Book copy with ID " + uniqueId + " removed from rack " + rack.getRackNumber() + ".");

            if (items.isEmpty()) {
                Book book = books.remove(isbn);
                Rack removedRack = bookRacks.remove(book);
                racks.remove(removedRack.getRackNumber());
                System.out.println("All copies of book removed. Rack " + removedRack.getRackNumber() + " is now empty.");
            }
        } else {
            System.out.println("No book found with unique ID: " + uniqueId);
        }
    }

    public void displayLibraryContents() {
        if (racks.isEmpty()) {
            System.out.println("The library has no racks.");
            return;
        }

        System.out.println("Library Contents:");
        for (Rack rack : racks.values()) {
            System.out.println("Rack Number: " + rack.getRackNumber());
            rack.displayRackContents();
        }
    }

    public BookItem borrowBook(String isbn, Patron patron) {

        // Check if the book exists in the library
        List<BookItem> availableItems = bookItems.getOrDefault(isbn, new ArrayList<>());
        if (availableItems.isEmpty()) {
            System.out.println("No available copies for the book with ISBN: " + isbn);
            return null;
        }

        // Find the first available book item
        BookItem bookItemToBorrow = null;
        for (BookItem item : availableItems) {
            if (item.isAvailable()) {
                bookItemToBorrow = item;
                break;
            }
        }

        if (bookItemToBorrow == null) {
            System.out.println("No available copies for the book with ISBN: " + isbn);
            return null;
        }

        // Create a transaction and update records
        LocalDateTime dueDate = LocalDateTime.now().plusDays(14); // Example: 14-day borrowing period
        //transactionManagement.Transaction transaction = new transactionManagement.Transaction(patron, bookItemToBorrow, dueDate);

        //patron.borrowBook(transaction, this);
        bookItemToBorrow.setAvailable(false);

        System.out.println("Book borrowed successfully. Due date: " + dueDate+" ISBN "+isbn+" ");
        return bookItemToBorrow;
    }

    public Map<String, Book> getBooks() {
        return books;
    }
}

package libraryManagement;

import bookManagementSystem.BookItem;

import java.util.ArrayList;
import java.util.List;

public class Rack {
    private int rackNumber;
    private List<BookItem> bookItems;

    public Rack(int rackNumber) {
        this.rackNumber = rackNumber;
        this.bookItems = new ArrayList<>();
    }

    public int getRackNumber() {
        return rackNumber;
    }

    public void addBookItem(BookItem bookItem) {
        bookItems.add(bookItem);
    }

    public void removeBookItem(BookItem bookItem) {
        bookItems.remove(bookItem);
    }

    public void displayRackContents() {
        System.out.println("Rack " + rackNumber + " contains:");
        if (bookItems.isEmpty()) {
            System.out.println("  No books in this rack.");
            return;
        }
        for (BookItem item : bookItems) {
            System.out.println("  - Book: " + item.getTitle() + " by " + item.getAuthor() +
                    " (ISBN: " + item.getISBN() + ")");
            System.out.println("    BookItem ID: " + item.getUniqueId() + ", Available: " + item.isAvailable());
        }
    }
}

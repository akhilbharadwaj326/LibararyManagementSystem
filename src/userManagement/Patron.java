package userManagement;

import libraryManagement.Library;
import transactionManagemnt.Transaction;


import java.util.ArrayList;
import java.util.List;

public class Patron extends Account {
    public static final int MAX_BOOKS_ALLOWED = 5;
    private List<Transaction> borrowedBooks;

    public Patron(String username, String name, String mobile, String address, String password) {
        super(username, password, name, mobile, address, "Patron");
        this.borrowedBooks = new ArrayList<>();
    }

    public void performPatronActions() {
        System.out.println("Welcome Patron! You can borrow and return books.");
        // Add patron-specific actions here (e.g., menu for borrowing and returning books).
    }

    public List<Transaction> getBorrowedBooks() {
        return borrowedBooks;
    }

    public boolean borrowBook(Transaction transaction, Library library) {
        if (borrowedBooks.size() >= MAX_BOOKS_ALLOWED) {
            System.out.println("You have reached the maximum borrowing limit.");
            return false;
        }
        borrowedBooks.add(transaction);
        return true;
    }

    public void returnBook(Transaction transaction, Library library) {
        if (borrowedBooks.remove(transaction)) {
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("You did not borrow this book.");
        }
    }
}

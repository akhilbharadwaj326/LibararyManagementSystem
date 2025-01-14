package transactionManagemnt;

import bookManagementSystem.BookItem;
import java.time.LocalDate;

public class Transaction {
    private String transactionId;
    private String patronId;
    private BookItem bookItem;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private static final int MAX_BORROW_DAYS = 15;
    private static final int MAX_BOOKS_ALLOWED = 5;
    private static final double LATE_FEE_PER_DAY = 1.0;
    private String status;

    public Transaction(String transactionId, String patronId, BookItem bookItem, LocalDate borrowDate) {
        this.transactionId = transactionId;
        this.patronId = patronId;
        this.bookItem = bookItem;
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusDays(MAX_BORROW_DAYS);
        this.status="Borrowed";
    }

    public String getStatus() {
        return status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getPatronId() {
        return patronId;
    }

    public BookItem getBookItem() {
        return bookItem;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void returnBook(LocalDate returnDate) {
        this.returnDate = returnDate;
        this.status = "returned";
        this.bookItem.setAvailable(true); // Mark the book as available again.
    }

    public double calculateLateFee() {
        if (returnDate == null || !returnDate.isAfter(dueDate)) {
            return 0.0; // No late fee if the book is returned on or before the due date.
        }
        long daysLate = returnDate.toEpochDay() - dueDate.toEpochDay();
        return daysLate * LATE_FEE_PER_DAY;
    }

    public static boolean canBorrowMoreBooks(int currentBooksBorrowed) {
        return currentBooksBorrowed < MAX_BOOKS_ALLOWED;
    }
}

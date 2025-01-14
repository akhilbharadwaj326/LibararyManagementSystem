import bookManagementSystem.Book;
import libraryManagement.Library;
import transactionManagemnt.Transaction;
import userManagement.Account;
import userManagement.Librarian;
import userManagement.Patron;
import bookManagementSystem.BookItem;
import paymentManagement.*;
import service.SearchService;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        List<Transaction> transactions = new ArrayList<>();

        while (true) {
            System.out.println("\nWelcome to the Library Management System!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (choice == 1) {
                try {
                    // Registration
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter mobile number: ");
                    String mobile = scanner.nextLine();
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter role (Librarian/Patron): ");
                    String role = scanner.nextLine();

                    Account.signup(username, password, name, mobile, address, role);
                }
                catch(Exception e){
                    System.out.println("Enter the details correctly");
                }

            } else if (choice == 2) {
                // Login
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                System.out.print("Enter role (Librarian/Patron): ");
                String role = scanner.nextLine();

                Account user = Account.findUser(username);
                if (user != null && user.verifyCredentials(username, password, role)) {
                    if (role.equalsIgnoreCase("Librarian")) {
                        Librarian librarian = new Librarian(username, user.getName(), user.getMobileNumber(), user.getAddress(), password);

                        // Librarian Actions
                        while (true) {
                            System.out.println("\nLibrarian Menu:");
                            System.out.println("1. Add Book");
                            System.out.println("2. Remove Book");
                            System.out.println("3. Display Library Contents");
                            System.out.println("4. Logout");
                            System.out.print("Choose an option: ");
                            int libChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline character

                            if (libChoice == 1) {
                                try {
                                    System.out.print("Enter book title: ");
                                    String title = scanner.nextLine();
                                    System.out.print("Enter author: ");
                                    String author = scanner.nextLine();
                                    System.out.print("Enter publication date (yyyy-MM-dd): ");
                                    LocalDateTime pubDate = LocalDate.parse(scanner.nextLine()).atStartOfDay();
                                    System.out.print("Enter category: ");
                                    String category = scanner.nextLine();
                                    System.out.print("Enter ISBN: ");
                                    String isbn = scanner.nextLine();

                                    librarian.addBook(title, author, pubDate, category, isbn, library);
                                }
                                catch(Exception e){
                                    System.out.println("Enter the details correctly");

                                }

                            } else if (libChoice == 2) {
                                System.out.print("Enter ISBN of the book to remove: ");
                                String isbn = scanner.nextLine();
                                System.out.print("Enter unique ID of the book copy: ");
                                String uniqueId = scanner.nextLine();

                                librarian.removeBook(isbn, uniqueId, library);

                            } else if (libChoice == 3) {
                                library.displayLibraryContents();

                            } else if (libChoice == 4) {
                                break;
                            }
                        }
                    } else if (role.equalsIgnoreCase("Patron")) {
                        Patron patron = new Patron(username, user.getName(), user.getMobileNumber(), user.getAddress(), password);

                        // Patron Actions
                        while (true) {
                            System.out.println("\nPatron Menu:");
                            System.out.println("1. Search Book");
                            System.out.println("2. Borrow Book");
                            System.out.println("3. Return Book");
                            System.out.println("4. View Borrowed Books");
                            System.out.println("5. Logout");
                            System.out.print("Choose an option: ");

                            int patChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline character
                            if (patChoice == 1) {
                                System.out.println("1. Search Book by title");
                                System.out.println("2. Borrow Book by author");
                                System.out.println("3. Return Book by ISBN");
                                System.out.print("Choose an option: ");
                                int searchChoice = scanner.nextInt();
                                List<Book> foundBooks;
                                if(searchChoice==1) {
                                    System.out.print("Enter book title to search: ");
                                    String title = scanner.nextLine();
                                    SearchService searchService = new SearchService(new ArrayList<>(library.getBooks().values()));
                                    foundBooks = searchService.searchByTitle(title);
                                }
                                else if(searchChoice==2){
                                    System.out.print("Enter book author to search: ");
                                    String author = scanner.nextLine();
                                    SearchService searchService = new SearchService(new ArrayList<>(library.getBooks().values()));
                                    foundBooks = searchService.searchByAuthor(author);
                                }
                                else {
                                    System.out.print("Enter book ISBN to search: ");
                                    String isbn = scanner.nextLine();
                                    SearchService searchService = new SearchService(new ArrayList<>(library.getBooks().values()));
                                    foundBooks = searchService.searchByISBN(isbn);
                                }
                                if (foundBooks.isEmpty()) {
                                    System.out.println("No books found with the selection ");
                                } else {
                                    foundBooks.forEach(book -> System.out.println("Found: " + book.getTitle() + " by " + book.getAuthor()+" ISBN number "+book.getISBN()));
                                }

                            } else if (patChoice == 2) {
                                System.out.print("Enter ISBN of the book to borrow: ");
                                String isbn = scanner.nextLine();

                                BookItem bookToBorrow = library.borrowBook(isbn,patron);
                                if (bookToBorrow != null) {
                                    Transaction transaction = new Transaction("T" + (transactions.size() + 1), username, bookToBorrow, LocalDate.now());
                                    transactions.add(transaction);
                                    patron.borrowBook(transaction, library);
                                } else {
                                    System.out.println("Book not available or already borrowed.");
                                }

                            } else if (patChoice == 3) {
                                System.out.print("Enter transaction ID of the book to return: ");
                                String transactionId = scanner.nextLine();

                                Transaction transactionToReturn = transactions.stream()
                                        .filter(t -> t.getTransactionId().equals(transactionId) && t.getPatronId().equals(username))
                                        .findFirst()
                                        .orElse(null);

                                if (transactionToReturn != null) {
                                    transactionToReturn.returnBook(LocalDate.now());
                                    patron.returnBook(transactionToReturn, library);

                                    double lateFee = transactionToReturn.calculateLateFee();
                                    if (lateFee > 0) {
                                        System.out.println("Late fee of $" + lateFee + " needs to be paid.");
                                        System.out.println("Choose payment method: 1. Cash 2. Credit Card 3. UPI");
                                        int paymentMethodChoice = scanner.nextInt();
                                        scanner.nextLine(); // Consume newline

                                        PaymentMethod paymentMethod;
                                        if (paymentMethodChoice == 1) {
                                            paymentMethod = new CashPayment();
                                        } else if (paymentMethodChoice == 2) {
                                            paymentMethod = new CreditCardPayment();
                                        } else {
                                            paymentMethod = new UPIPayment();
                                        }
                                        paymentMethod.pay(lateFee);
                                    }
                                } else {
                                    System.out.println("No such transaction found.");
                                }

                            } else if (patChoice == 4) {
                                transactions.stream()
                                        .filter(t -> t.getPatronId().equals(username))
                                        .forEach(t -> System.out.println("Transaction ID: " + t.getTransactionId() + ", Book: " + t.getBookItem().getTitle() +
                                                ", Due Date: " + t.getDueDate()+" status "+t.getStatus()));

                            } else if (patChoice == 5) {
                                break;
                            }
                        }
                    }
                } else {
                    System.out.println("Invalid credentials or role.");
                }

            } else if (choice == 3) {
                System.out.println("Exiting Library Management System. Goodbye!");
                break;
            }
        }

        scanner.close();
    }
}

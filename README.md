# Library Management System
This Library Management System is a comprehensive solution for managing books, users, and transactions in a library. The system allows users to borrow and return books, provides search functionality, and supports librarian-specific tasks such as adding and removing books.

The project is implemented using Java and follows an Object-Oriented Programming (OOP) approach to ensure modularity, scalability, and ease of maintenance.

## Key Features
### User Management

#### Librarian:
  Add or remove books from the library.
  Manage racks and book placement.
#### Patron:

Borrow books (up to 5 at a time).

Return borrowed books.

Book Management

Books are categorized with details such as:

Title

Author

Publication Date

Category

ISBN

Each book has unique BookItem copies with distinct IDs.

Books are assigned to racks for physical organization.

Library Features

Add new books and assign racks dynamically.

Remove books, including their specific copies.

Display the contents of the library with details about racks and books.

Payment Management

Multiple payment methods are supported:

Cash

Credit Card

UPI

Transaction Management

Maintain records of borrowed and returned books.

Calculate late fees based on due dates.

Search Functionality

Search for books by title, author, or ISBN using the SearchService.

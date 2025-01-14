package bookManagementSystem;

import java.time.LocalDateTime;

public class Book {
     private String title;
     private String author;
     private LocalDateTime publicationDate;
     private String category;
     private String ISBN;

    public Book(String title, String author, LocalDateTime publicationDate, String category, String ISBN) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.category = category;
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
}

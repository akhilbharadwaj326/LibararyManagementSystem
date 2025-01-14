package service;

import bookManagementSystem.Book;

import java.util.List;
import java.util.stream.Collectors;

public class SearchService {
    private List<Book> books;

    public SearchService(List<Book> books) {
        this.books = books;
    }

    public List<Book> searchByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    public List<Book> searchByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public List<Book> searchByISBN(String author) {
        return books.stream()
                .filter(book -> book.getISBN().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }
}

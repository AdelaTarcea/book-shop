package ro.ubb.bookstore.core.service;

import ro.ubb.bookstore.core.domain.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    Book saveBook(Book book);

    Book updateBook(Long id, Book book);

    void deleteById(Long bookId);
}

package ro.ubb.bookstore.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.bookstore.core.domain.Book;
import ro.ubb.bookstore.core.repository.BookRepository;

import java.util.List;
import java.util.Optional;
@Service
public class BookServiceImpl implements BookService {
    private static final Logger log =
            LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;


    @Override
    public List<Book> getAllBooks() {
        log.trace("getAllBooks --- method entered");

        List<Book> result = bookRepository.findAll();

        log.trace("getAllBooks: result={}", result);

        return result;
    }

    @Override

    public Book saveBook(Book book) {
        log.trace("saveBook: book={}", book);
        Book result = bookRepository.save(book);
        log.trace("saveBook: result={}", result);
        return result;
    }

    @Override
    @Transactional
    public Book updateBook(Long id, Book book) {
        log.trace("updateBook: id={}, book={}", id, book);

        Optional<Book> optional = bookRepository.findById(id);

        Book result = optional.orElse(book);
        result.setName(book.getName());
        result.setAuthor(book.getAuthor());
        result.setYear(book.getYear());
        result.setStock(book.getStock());
        result.setPrice(book.getPrice());

        log.trace("updateBook: result={}", result);

        return result;
    }

    @Override
    public void deleteById(Long bookId) {
        log.trace("deleteById: bookId={}", bookId);

        bookRepository.deleteById(bookId);

        log.trace("deleteById --- method finished");
    }
}


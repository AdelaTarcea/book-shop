package ro.ubb.bookstore.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.bookstore.core.domain.Book;
import ro.ubb.bookstore.core.service.BookService;
import ro.ubb.bookstore.web.converter.BookConverter;
import ro.ubb.bookstore.web.dto.BookDto;
import ro.ubb.bookstore.web.dto.BooksDto;

import java.util.List;
import java.util.Set;

@RestController
public class BookController {

    private static final Logger log =
            LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private BookConverter bookConverter;


    @RequestMapping(value = "/books", method = RequestMethod.GET)
    BooksDto getBooks() {
        log.trace("getBooks --- method entered");

        List<Book> books = bookService.getAllBooks();
        Set<BookDto> bookDto = bookConverter.convertModelsToDtos(books);
        BooksDto result = new BooksDto(bookDto);

        log.trace("getBooks: result={}", result);

        return result;
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    BookDto saveBook(@RequestBody BookDto dto) {
        log.trace("saveBook: dto={}", dto);

        Book book = bookService.saveBook(
                        bookConverter.convertDtoToModel(dto));
        BookDto result = bookConverter.convertModelToDto(book);

        log.trace("saveBook: result={}", result);

        return result;
    }

    @RequestMapping(value = "/books/{bookId}", method = RequestMethod.PUT)
    BookDto updateBook(@PathVariable Long bookId,
                       @RequestBody BookDto dto) {
        log.trace("updateBook: bookId={}, dto={}", bookId, dto);

        Book book = bookService.updateBook(bookId, bookConverter.convertDtoToModel(dto));

        BookDto result = bookConverter.convertModelToDto(book);

        log.trace("updateBook: result={}", result);

        return result;
    }

    @RequestMapping(value = "/books/{bookId}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteBook(@PathVariable Long bookId) {
        log.trace("deletebook: bookId={}", bookId);

        bookService.deleteById(bookId);

        log.trace("deleteBook --- method finished");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}


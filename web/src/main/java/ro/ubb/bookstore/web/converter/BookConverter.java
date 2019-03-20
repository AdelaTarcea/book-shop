package ro.ubb.bookstore.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.bookstore.core.domain.Book;
import ro.ubb.bookstore.web.dto.BookDto;

@Component
public class BookConverter extends BaseConverter<Book, BookDto> {
    @Override
    public Book convertDtoToModel(BookDto dto) {
        Book book = new Book(dto.getName(), dto.getAuthor(), dto.getYear(), dto.getStock(), dto.getPrice());
        book.setId(dto.getId());
        return book;
    }

    @Override
    public BookDto convertModelToDto(Book book) {
        BookDto dto = new BookDto(book.getName(), book.getAuthor(), book.getYear(), book.getStock(), book.getPrice());
        dto.setId(book.getId());
        return dto;
    }
}
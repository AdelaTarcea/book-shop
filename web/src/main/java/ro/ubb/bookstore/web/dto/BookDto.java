package ro.ubb.bookstore.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class BookDto extends BaseDto {
    private String name, author;
    private int year, stock, price;
}
package ro.ubb.bookstore.core.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Book extends BaseEntity<Long> {
    private String name, author;
    private int year, stock, price;
}

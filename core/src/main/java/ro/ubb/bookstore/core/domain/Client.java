package ro.ubb.bookstore.core.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Client extends BaseEntity<Long> {
    private String name;
    private Integer cnp;
}

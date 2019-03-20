package ro.ubb.bookstore.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class ClientDto extends BaseDto {
    private String name;
    private Integer cnp;
}

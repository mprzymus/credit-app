package pl.mprzymus.springbootcredit.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDto {
    private String firstName;
    private String surname;
    private String pesel;
}

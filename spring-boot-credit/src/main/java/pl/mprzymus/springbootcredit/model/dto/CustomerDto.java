package pl.mprzymus.springbootcredit.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CustomerDto {
    @NotBlank
    private String firstName;

    @NotBlank
    private String surname;

    @Size(min = 11, max = 11)
    private String pesel;
}

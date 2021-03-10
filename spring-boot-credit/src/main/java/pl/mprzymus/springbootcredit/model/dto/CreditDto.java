package pl.mprzymus.springbootcredit.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class CreditDto {
    @NotEmpty
    private String creditName;
}

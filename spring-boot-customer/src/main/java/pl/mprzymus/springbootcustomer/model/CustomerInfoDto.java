package pl.mprzymus.springbootcustomer.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CustomerInfoDto {
    @Valid
    @NotNull
    private CustomerDto customer;

    @NotNull
    private Long creditNumber;
}

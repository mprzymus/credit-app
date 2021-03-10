package pl.mprzymus.springbootcredit.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CreditInfo {
    @Valid
    @NotNull
    private CustomerDto customer;

    @Valid
    @NotNull
    private CreditDto credit;

    @Valid
    @NotNull
    private ProductDto product;
}

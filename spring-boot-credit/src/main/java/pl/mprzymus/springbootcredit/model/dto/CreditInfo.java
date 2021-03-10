package pl.mprzymus.springbootcredit.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreditInfo {
    private CustomerDto customer;
    private CreditDto credit;
    private ProductDto product;
}

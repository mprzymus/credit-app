package pl.mprzymus.springbootcustomer.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CustomerDtoList {
    private List<CustomerInfoDto> customers;
}

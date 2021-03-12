package pl.mprzymus.springbootcustomer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDtoList {
    private List<CustomerInfoDto> customers;
}

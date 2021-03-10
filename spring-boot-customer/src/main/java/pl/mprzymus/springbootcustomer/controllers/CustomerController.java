package pl.mprzymus.springbootcustomer.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mprzymus.springbootcustomer.mapper.CustomerMapper;
import pl.mprzymus.springbootcustomer.model.CustomerInfoDto;
import pl.mprzymus.springbootcustomer.service.CustomerService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @PostMapping
    public CustomerInfoDto createCustomer(@Valid @RequestBody CustomerInfoDto customerInfoDto) {
        var customer = customerService.saveOrUpdate(customerInfoDto);
        var toReturn = new CustomerInfoDto();
        toReturn.setCreditNumber(customer.getCreditId());
        toReturn.setCustomer(customerMapper.customerToCustomerDto(customer));
        return toReturn;
    }
}

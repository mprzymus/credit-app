package pl.mprzymus.springbootcustomer.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.mprzymus.springbootcustomer.model.CustomerDtoList;
import pl.mprzymus.springbootcustomer.model.CustomerInfoDto;
import pl.mprzymus.springbootcustomer.service.CustomerService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public CustomerInfoDto createCustomer(@Valid @RequestBody CustomerInfoDto customerInfoDto) {
        return customerService.saveCustomer(customerInfoDto);
    }

    @GetMapping
    public CustomerDtoList getCustomers() {
        return customerService.findAll();
    }
}

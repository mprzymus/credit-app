package pl.mprzymus.springbootcustomer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mprzymus.springbootcustomer.mapper.CustomerMapper;
import pl.mprzymus.springbootcustomer.model.Customer;
import pl.mprzymus.springbootcustomer.model.CustomerInfoDto;
import pl.mprzymus.springbootcustomer.repository.CustomerRepository;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public Customer saveOrUpdate(CustomerInfoDto customer) {
        var found = customerRepository.findByPesel(customer.getCustomer().getPesel());
        var toSave = customerMapper.customerInfoDtoToCustomer(customer);
        found.ifPresent(value -> toSave.setId(value.getId()));
        return customerRepository.save(toSave);
    }
}

package pl.mprzymus.springbootcustomer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mprzymus.springbootcustomer.mapper.CustomerMapper;
import pl.mprzymus.springbootcustomer.model.CustomerDtoList;
import pl.mprzymus.springbootcustomer.model.CustomerInfoDto;
import pl.mprzymus.springbootcustomer.repository.CustomerRepository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerInfoDto saveOrUpdate(CustomerInfoDto customer) {
        var found = customerRepository.findByPesel(customer.getCustomer().getPesel());
        var toSave = customerMapper.customerInfoDtoToCustomer(customer);
        found.ifPresent(value -> toSave.setId(value.getId()));
        var saved = customerRepository.save(toSave);
        var toReturn = new CustomerInfoDto();
        toReturn.setCreditNumber(saved.getCreditId());
        toReturn.setCustomer(customerMapper.customerToCustomerDto(saved));
        return toReturn;
    }

    public CustomerDtoList findAll() {
        var allIterable = customerRepository.findAll();
        var allList = StreamSupport.stream(allIterable.spliterator(), false)
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
        var customerDtoList = new CustomerDtoList();
        customerDtoList.setCustomers(allList);
        return customerDtoList;
    }
}

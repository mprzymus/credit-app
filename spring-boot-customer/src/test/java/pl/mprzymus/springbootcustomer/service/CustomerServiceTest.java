package pl.mprzymus.springbootcustomer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mprzymus.springbootcustomer.mapper.CustomerMapper;
import pl.mprzymus.springbootcustomer.model.Customer;
import pl.mprzymus.springbootcustomer.model.CustomerInfoDto;
import pl.mprzymus.springbootcustomer.repository.CustomerRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    public static final long CREDIT_NUMBER = 1L;
    public static final String PESEL = "1234";
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerService = new CustomerService(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    void saveCustomer() {
        var customerInfoDto = new CustomerInfoDto();
        customerInfoDto.setCreditNumber(CREDIT_NUMBER);

        var customer = new Customer();
        customer.setCreditId(CREDIT_NUMBER);
        customer.setPesel(PESEL);

        when(customerRepository.save(any())).thenReturn(customer);

        var saved = customerService.saveCustomer(customerInfoDto);

        assertEquals(PESEL, saved.getCustomer().getPesel());
        assertEquals(CREDIT_NUMBER, saved.getCreditNumber());

        verify(customerRepository).save(any());
    }

    @Test
    void findAllOnEmpty() {
        when(customerRepository.findAll()).thenReturn(List.of());

        var result = customerService.findAll();

        assertNotNull(result.getCustomers());
        assertEquals(0, result.getCustomers().size());

        verify(customerRepository).findAll();
    }

    @Test
    void findAll() {
        var customer1 = new Customer();
        customer1.setPesel(PESEL);
        customer1.setCreditId(CREDIT_NUMBER);
        when(customerRepository.findAll()).thenReturn(List.of(customer1));

        var result = customerService.findAll();

        assertEquals(1, result.getCustomers().size());
        var foundCustomer = result.getCustomers().get(0);
        assertEquals(PESEL, foundCustomer.getCustomer().getPesel());
        assertEquals(CREDIT_NUMBER, foundCustomer.getCreditNumber());

        verify(customerRepository).findAll();
    }
}
package pl.mprzymus.springbootcustomer.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.mprzymus.springbootcustomer.model.CustomerDto;
import pl.mprzymus.springbootcustomer.model.CustomerInfoDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerMapperTest {

    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;
    private CustomerInfoDto customerInfoDto;

    @BeforeEach
    void setUp() {
        customerInfoDto = new CustomerInfoDto();
        var customerDto = new CustomerDto();
        customerDto.setPesel("PESEL");
        customerDto.setSurname("SURNAME");
        customerDto.setFirstName("FIRST_NAME");
        customerInfoDto.setCustomer(customerDto);
        customerInfoDto.setCreditNumber(1L);
    }

    @Test
    void customerInfoDtoToCustomer() {
        var customer = customerMapper.customerInfoDtoToCustomer(customerInfoDto);
        var customerDto = customerInfoDto.getCustomer();

        assertEquals(customer.getCreditId(), customerInfoDto.getCreditNumber());
        assertEquals(customer.getPesel(), customerDto.getPesel());
        assertEquals(customer.getFirstName(), customerDto.getFirstName());
        assertEquals(customer.getSurname(), customerDto.getSurname());
    }
}
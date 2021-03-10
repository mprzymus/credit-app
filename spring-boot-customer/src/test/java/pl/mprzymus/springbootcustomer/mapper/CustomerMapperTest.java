package pl.mprzymus.springbootcustomer.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.mprzymus.springbootcustomer.model.Customer;
import pl.mprzymus.springbootcustomer.model.CustomerDto;
import pl.mprzymus.springbootcustomer.model.CustomerInfoDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerMapperTest {

    public static final String PESEL = "PESEL";
    public static final String SURNAME = "SURNAME";
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final long CREDIT_NUMBER = 1L;
    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;


    @Test
    void customerInfoDtoToCustomer() {
        CustomerInfoDto customerInfoDto = new CustomerInfoDto();
        var customerDto = new CustomerDto();
        customerDto.setPesel(PESEL);
        customerDto.setSurname(SURNAME);
        customerDto.setFirstName(FIRST_NAME);
        customerInfoDto.setCustomer(customerDto);
        customerInfoDto.setCreditNumber(CREDIT_NUMBER);

        var customer = customerMapper.customerInfoDtoToCustomer(customerInfoDto);

        assertEquals(customer.getCreditId(), customerInfoDto.getCreditNumber());
        assertEquals(customer.getPesel(), customerDto.getPesel());
        assertEquals(customer.getFirstName(), customerDto.getFirstName());
        assertEquals(customer.getSurname(), customerDto.getSurname());
    }

    @Test
    void customerToCustomerDto() {
        var customer = new Customer();
        customer.setFirstName(FIRST_NAME);
        customer.setSurname(SURNAME);
        customer.setPesel(PESEL);
        customer.setCreditId(CREDIT_NUMBER);

        var dto = customerMapper.customerToCustomerDto(customer);

        assertEquals(customer.getSurname(), dto.getSurname());
        assertEquals(customer.getFirstName(), dto.getFirstName());
        assertEquals(customer.getPesel(), dto.getPesel());
    }
}
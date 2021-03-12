package pl.mprzymus.springbootcustomer.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.mprzymus.springbootcustomer.model.CustomerDto;
import pl.mprzymus.springbootcustomer.model.CustomerDtoList;
import pl.mprzymus.springbootcustomer.model.CustomerInfoDto;
import pl.mprzymus.springbootcustomer.service.CustomerService;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    private final static String URL = "/api/customers";
    public static final long CREDIT_ID = 1L;
    public static final String PESEL = "12312312312";
    public static final String SURNAME = "Surname";
    public static final String FIRST_NAME = "FirstName";

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .build();
    }

    @Test
    void createCreditsInvalidInput() throws Exception {
        var invalidData = new CustomerInfoDto();
        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(invalidData)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createCustomer() throws Exception {
        var customerInfoDto = new CustomerInfoDto();
        var customerDto = new CustomerDto();
        customerInfoDto.setCreditNumber(CREDIT_ID);
        customerInfoDto.setCustomer(customerDto);
        customerDto.setPesel(PESEL);
        customerDto.setSurname(SURNAME);
        customerDto.setFirstName(FIRST_NAME);

        when(customerService.saveCustomer(any())).thenReturn(customerInfoDto);

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(customerInfoDto)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.customer.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.customer.surname", equalTo(SURNAME)))
                .andExpect(jsonPath("$.customer.pesel", equalTo(PESEL)));

        verify(customerService).saveCustomer(any());
    }

    @Test
    void getCustomers() throws Exception {
        var customer1 = new CustomerInfoDto();
        var customer2 = new CustomerInfoDto();

        when(customerService.findAll()).thenReturn(new CustomerDtoList(List.of(customer1, customer2)));

        mockMvc.perform(get(URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));

        verify(customerService).findAll();
    }
}
package pl.mprzymus.springbootcredit.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import pl.mprzymus.springbootcredit.model.Credit;
import pl.mprzymus.springbootcredit.model.dto.*;
import pl.mprzymus.springbootcredit.service.CreditInfoCreationService;
import pl.mprzymus.springbootcredit.service.CreditService;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CreditControllerTest {

    private final static String URL = "/api/credits";
    public static final String CREDIT_NAME = "Credit_name";
    public static final int VALUE = 12;
    public static final String PRODUCT_NAME = "ProductName";
    public static final String PESEL = "12312312312";
    public static final String FIRST_NAME = "FirstName";
    public static final String SURNAME = "Surname";
    public static final long ID = 1L;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CreditService creditService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        CreditController creditController = new CreditController(restTemplate, creditService, new CreditInfoCreationService());

        mockMvc = MockMvcBuilders.standaloneSetup(creditController)
                .build();
    }

    @Test
    void createCreditsInvalidInput() throws Exception {
        var invalidData = new CreditInfo();
        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(invalidData)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createCredits() throws Exception {
        CreditInfo credit = createCreditInfo();
        var saved = new Credit();
        saved.setId(ID);
        saved.setCreditName(CREDIT_NAME);

        when(creditService.saveCredit(anyString())).thenReturn(saved);

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(credit)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.creditNumber", equalTo(ID)));

    }

    private CreditInfo createCreditInfo() {
        var credit = new CreditInfo();

        credit.setCredit(new CreditDto());
        credit.setCustomer(new CustomerDto());
        credit.setProduct(new ProductDto());

        credit.getCredit().setCreditName(CREDIT_NAME);
        credit.getCustomer().setSurname(SURNAME);
        credit.getCustomer().setFirstName(FIRST_NAME);
        credit.getCustomer().setPesel(PESEL);
        credit.getProduct().setProductName(PRODUCT_NAME);
        credit.getProduct().setValue(VALUE);
        return credit;
    }


    @Test
    void getCredits() throws Exception {
        var credit1 = new Credit();
        credit1.setId(1L);
        var credit2 = new Credit();
        credit2.setId(2L);

        var product1 = new ProductInfoDto();
        product1.setCreditNumber(1L);
        var product2 = new ProductInfoDto();
        product2.setCreditNumber(2L);

        var customer1 = new CustomerInfoDto();
        customer1.setCreditNumber(1L);
        var customer2 = new CustomerInfoDto();
        customer2.setCreditNumber(2L);

        when(creditService.findAllCredits()).thenReturn(List.of(credit1, credit2));
        when(restTemplate.getForObject(eq(CreditController.PRODUCTS_URL), any()))
                .thenReturn(new ProductDtoList(List.of(product1, product2)));
        when(restTemplate.getForObject(eq(CreditController.CUSTOMERS_URL), any()))
                .thenReturn(new CustomerDtoList(List.of(customer2, customer1)));

        mockMvc.perform(get(URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.credits", hasSize(2)));

        verify(creditService).findAllCredits();
        verify(restTemplate).getForObject(eq(CreditController.PRODUCTS_URL), any());
        verify(restTemplate).getForObject(eq(CreditController.CUSTOMERS_URL), any());
    }

    @Test
    void getCreditsEmptyData() throws Exception {
        when(creditService.findAllCredits()).thenReturn(List.of());
        when(restTemplate.getForObject(eq(CreditController.PRODUCTS_URL), any()))
                .thenReturn(new ProductDtoList(List.of()));
        when(restTemplate.getForObject(eq(CreditController.CUSTOMERS_URL), any()))
                .thenReturn(new CustomerDtoList(List.of()));

        mockMvc.perform(get(URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.credits", hasSize(0)));

        verify(creditService).findAllCredits();
        verify(restTemplate).getForObject(eq(CreditController.PRODUCTS_URL), any());
        verify(restTemplate).getForObject(eq(CreditController.CUSTOMERS_URL), any());

    }
}
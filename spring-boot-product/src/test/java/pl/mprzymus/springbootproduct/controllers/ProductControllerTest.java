package pl.mprzymus.springbootproduct.controllers;

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
import pl.mprzymus.springbootproduct.model.ProductDto;
import pl.mprzymus.springbootproduct.model.ProductDtoList;
import pl.mprzymus.springbootproduct.model.ProductInfoDto;
import pl.mprzymus.springbootproduct.service.ProductService;

import javax.print.attribute.standard.MediaSize;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({MockitoExtension.class})
class ProductControllerTest {

    private final static String URL = "/api/products";
    public static final long CREDIT_ID = 1L;
    public static final String NAME = "Name";
    public static final int VALUE = 2;

    private MockMvc mockMvc;

    @Mock
    private ProductService customerService;

    @InjectMocks
    private ProductController customerController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .build();
    }

    @Test
    void createCreditsInvalidInput() throws Exception {
        var invalidData = new ProductInfoDto();
        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(invalidData)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createCustomer() throws Exception {
        var productInfoDto = new ProductInfoDto();
        var productDto = new ProductDto();
        productInfoDto.setCreditNumber(CREDIT_ID);
        productInfoDto.setProduct(productDto);
        productDto.setProductName(NAME);
        productDto.setValue(VALUE);

        when(customerService.saveProduct(any())).thenReturn(productInfoDto);

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(productInfoDto)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.product.productName", equalTo(NAME)))
                .andExpect(jsonPath("$.product.value", equalTo(VALUE)));

        verify(customerService).saveProduct(any());
    }

    @Test
    void getCustomers() throws Exception {
        var product1 = new ProductInfoDto();
        var product2 = new ProductInfoDto();

        when(customerService.findAll()).thenReturn(new ProductDtoList(List.of(product1, product2)));

        mockMvc.perform(get(URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products", hasSize(2)));

        verify(customerService).findAll();
    }
}
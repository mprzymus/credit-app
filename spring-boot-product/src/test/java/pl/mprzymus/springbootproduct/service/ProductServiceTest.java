package pl.mprzymus.springbootproduct.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mprzymus.springbootproduct.mapper.ProductMapper;
import pl.mprzymus.springbootproduct.model.Product;
import pl.mprzymus.springbootproduct.model.ProductInfoDto;
import pl.mprzymus.springbootproduct.repository.ProductRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    public static final long CREDIT_NUMBER = 1L;
    public static final String PRODUCT_NAME = "Name";
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository, ProductMapper.INSTANCE);
    }

    @Test
    void saveProduct() {
        var productInfoDto = new ProductInfoDto();
        productInfoDto.setCreditNumber(CREDIT_NUMBER);

        var product = new Product();
        product.setCreditId(CREDIT_NUMBER);
        product.setProductName(PRODUCT_NAME);

        when(productRepository.save(any())).thenReturn(product);

        var saved = productService.saveProduct(productInfoDto);

        assertEquals(CREDIT_NUMBER, saved.getCreditNumber());
        assertEquals(PRODUCT_NAME, saved.getProduct().getProductName());

        verify(productRepository).save(any());
    }

    @Test
    void findAllOnEmpty() {
        when(productRepository.findAll()).thenReturn(List.of());

        var result = productService.findAll();

        assertNotNull(result.getProducts());
        assertEquals(0, result.getProducts().size());

        verify(productRepository).findAll();
    }

    @Test
    void findAll() {
        var product = new Product();
        product.setProductName(PRODUCT_NAME);
        product.setCreditId(CREDIT_NUMBER);
        when(productRepository.findAll()).thenReturn(List.of(product));

        var result = productService.findAll();

        assertEquals(1, result.getProducts().size());
        var foundCustomer = result.getProducts().get(0);
        assertEquals(PRODUCT_NAME, foundCustomer.getProduct().getProductName());
        assertEquals(CREDIT_NUMBER, foundCustomer.getCreditNumber());

        verify(productRepository).findAll();
    }
}
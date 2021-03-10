package pl.mprzymus.springbootproduct.mapper;

import org.junit.jupiter.api.Test;
import pl.mprzymus.springbootproduct.model.Product;
import pl.mprzymus.springbootproduct.model.ProductDto;
import pl.mprzymus.springbootproduct.model.ProductInfoDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductMapperTest {
    public static final long CREDIT_NUMBER = 1L;
    public static final String PRODUCT_NAME = "PRODUCT_NAME";
    public static final int VALUE = 1;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    @Test
    void productInfoDtoToProduct() {
        var productDto = new ProductDto();
        productDto.setProductName(PRODUCT_NAME);
        productDto.setValue(VALUE);
        ProductInfoDto productInfoDto = new ProductInfoDto();
        productInfoDto.setCreditNumber(CREDIT_NUMBER);
        productInfoDto.setProduct(productDto);
        
        var product = productMapper.productInfoDtoToProduct(productInfoDto);

        assertEquals(CREDIT_NUMBER, product.getCreditId());
        assertEquals(VALUE, product.getValue());
        assertEquals(PRODUCT_NAME, product.getProductName());

    }

    @Test
    void productToProductDto() {
        var product = new Product();
        product.setProductName(PRODUCT_NAME);
        product.setValue(VALUE);

        var productDto = productMapper.productToProductDto(product);

        assertEquals(VALUE, productDto.getValue());
        assertEquals(PRODUCT_NAME, productDto.getProductName());
    }
}
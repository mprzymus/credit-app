package pl.mprzymus.springbootcredit.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.mprzymus.springbootcredit.model.Credit;
import pl.mprzymus.springbootcredit.model.dto.*;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreditInfoCreationServiceTest {

    public static final String PESEL = "PESEL";
    public static final String FIRST_NAME = "FirstName";
    public static final String SURNAME = "Surname";
    public static final String PRODUCT_NAME = "Name";
    public static final String CREDIT_NAME = "CreditName";
    public static final int VALUE = 123;
    public static final long ID = 1L;
    private CreditInfoCreationService creditInfoCreationService;

    @BeforeEach
    void setUp() {
        creditInfoCreationService = new CreditInfoCreationService();
    }

    @Test
    void createCreditInfoListTest() {
        var customers = new HashMap<Long, CustomerDto>();
        var customerDto = new CustomerDto();
        customerDto.setPesel(PESEL);
        customerDto.setFirstName(FIRST_NAME);
        customerDto.setSurname(SURNAME);
        customers.put(ID, customerDto);

        var products = new HashMap<Long, ProductDto>();
        var productDto = new ProductDto();
        productDto.setProductName(PRODUCT_NAME);
        productDto.setValue(VALUE);
        products.put(ID, productDto);

        var credit = new Credit();
        credit.setId(ID);
        credit.setCreditName(CREDIT_NAME);
        var credits = List.of(credit);

        var result = creditInfoCreationService.createCreditInfoList(products, customers, credits);
        var creditsList = result.getCredits();

        assertEquals(1, creditsList.size());

        var resultCredit = creditsList.get(0);

        assertEquals(FIRST_NAME, resultCredit.getCustomer().getFirstName());
        assertEquals(SURNAME, resultCredit.getCustomer().getSurname());
        assertEquals(PESEL, resultCredit.getCustomer().getPesel());

        assertEquals(PRODUCT_NAME, resultCredit.getProduct().getProductName());
        assertEquals(VALUE, resultCredit.getProduct().getValue());

        assertEquals(CREDIT_NAME, resultCredit.getCredit().getCreditName());
    }

    @Test
    void createCreditInfoListManyElementsTest() {
        var customers = new HashMap<Long, CustomerDto>();
        customers.put(1L, new CustomerDto());
        customers.put(2L, new CustomerDto());

        var products = new HashMap<Long, ProductDto>();
        products.put(1L, new ProductDto());
        products.put(2L, new ProductDto());

        var credit1 = new Credit();
        credit1.setId(1L);
        var credit2 = new Credit();
        credit2.setId(2L);
        var credits = List.of(credit1, credit2);

        var result = creditInfoCreationService.createCreditInfoList(products, customers, credits);
        var creditsList = result.getCredits();

        assertEquals(credits.size(), creditsList.size());
        creditsList.forEach(element -> {
            assertNotNull(element.getCredit());
            assertNotNull(element.getProduct());
            assertNotNull(element.getCustomer());
        });
    }

    @Test
    void createProductsMapTest() {
        var product1 = new ProductInfoDto();
        product1.setProduct(new ProductDto());
        product1.setCreditNumber(1L);
        var product2 = new ProductInfoDto();
        product2.setProduct(new ProductDto());
        product2.setCreditNumber(2L);
        List<ProductInfoDto> products = List.of(product1, product2);
        ProductDtoList productDtoList = new ProductDtoList(products);
        var result = creditInfoCreationService.createProductsMap(productDtoList);

        assertEquals(2, result.size());
        assertTrue(result.containsKey(1L));
        assertTrue(result.containsKey(2L));
    }

    @Test
    void createCustomersMapTest() {
        var customer1 = new CustomerInfoDto();
        customer1.setCustomer(new CustomerDto());
        customer1.setCreditNumber(1L);
        var customer2 = new CustomerInfoDto();
        customer2.setCustomer(new CustomerDto());
        customer2.setCreditNumber(2L);
        List<CustomerInfoDto> customers = List.of(customer1, customer2);
        CustomerDtoList productDtoList = new CustomerDtoList(customers);
        var result = creditInfoCreationService.createCustomersMap(productDtoList);

        assertEquals(2, result.size());
        assertTrue(result.containsKey(1L));
        assertTrue(result.containsKey(2L));
    }
}
package pl.mprzymus.springbootcredit.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.mprzymus.springbootcredit.model.dto.*;
import pl.mprzymus.springbootcredit.service.CreditInfoCreationService;
import pl.mprzymus.springbootcredit.service.CreditService;

import javax.validation.Valid;
import java.util.HashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/credits")
public class CreditController {

    public static final String PRODUCTS_URL = "http://product-service/api/products";
    public static final String CUSTOMERS_URL = "http://customer-service/api/customers";
    private final RestTemplate restTemplate;
    private final CreditService creditService;
    private final CreditInfoCreationService creditInfoCreationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreditNumberDto createCredits(@RequestBody @Valid CreditInfo creditInfo) {
        log.info("New credit for user {} {}", creditInfo.getCustomer().getFirstName(),
                creditInfo.getCustomer().getSurname() );
        var productInfoDto = new ProductInfoDto();
        var saved = creditService.saveCredit(creditInfo.getCredit().getCreditName());
        productInfoDto.setCreditNumber(saved.getId());
        productInfoDto.setProduct(creditInfo.getProduct());
        restTemplate.postForObject
                (PRODUCTS_URL, productInfoDto, String.class);
        var customerInfoDto = new CustomerInfoDto();
        customerInfoDto.setCustomer(creditInfo.getCustomer());
        customerInfoDto.setCreditNumber(saved.getId());
        restTemplate.postForObject
                (CUSTOMERS_URL, customerInfoDto, String.class);
        return new CreditNumberDto(saved.getId());
    }

    @GetMapping
    public CreditInfoList getCredits() {
        var credits = creditService.findAllCredits();
        var customers = restTemplate.getForObject(CUSTOMERS_URL, CustomerDtoList.class);
        var products = restTemplate.getForObject(PRODUCTS_URL, ProductDtoList.class);
        HashMap<Long, CustomerDto> customersMap = creditInfoCreationService.createCustomersMap(customers);
        HashMap<Long, ProductDto> productsMap = creditInfoCreationService.createProductsMap(products);
        return creditInfoCreationService.createCreditInfoList(productsMap, customersMap, credits);
    }
}

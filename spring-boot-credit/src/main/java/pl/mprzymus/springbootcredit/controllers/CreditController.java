package pl.mprzymus.springbootcredit.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.mprzymus.springbootcredit.model.Credit;
import pl.mprzymus.springbootcredit.model.dto.CreditInfo;
import pl.mprzymus.springbootcredit.model.dto.CustomerInfoDto;
import pl.mprzymus.springbootcredit.model.dto.ProductInfoDto;
import pl.mprzymus.springbootcredit.repository.CreditRepository;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/credits")
public class CreditController {

    private final RestTemplate restTemplate;
    private final CreditRepository creditRepository;

    @PostMapping
    public String createCredits(@RequestBody @Valid CreditInfo creditInfo) {
        log.info("Called");
        var number = createCreditNumber();
        var productInfoDto = new ProductInfoDto();
        productInfoDto.setCreditNumber(number);
        productInfoDto.setProduct(creditInfo.getProduct());
        var productCall = restTemplate.postForObject
                ("http://product-service/api/products", productInfoDto, String.class);
        var customerInfoDto = new CustomerInfoDto();
        customerInfoDto.setCustomer(creditInfo.getCustomer());
        customerInfoDto.setCreditNumber(number);
        var customerCall = restTemplate.postForObject
                ("http://customer-service/api/customers", customerInfoDto, String.class);
        var toSave = new Credit();
        toSave.setCreditName(creditInfo.getCredit().getCreditName());
        creditRepository.save(toSave);
        return number;
    }

    private String createCreditNumber() {
        //TODO generate number
        return "someNumber";
    }
}

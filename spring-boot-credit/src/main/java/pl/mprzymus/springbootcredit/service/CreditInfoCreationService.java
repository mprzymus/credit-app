package pl.mprzymus.springbootcredit.service;

import org.springframework.stereotype.Service;
import pl.mprzymus.springbootcredit.model.Credit;
import pl.mprzymus.springbootcredit.model.dto.*;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class CreditInfoCreationService {
    public CreditInfoList createCreditInfoList(HashMap<Long, ProductDto> products, HashMap<Long, CustomerDto> customers, Iterable<Credit> credits) {
        var list = new ArrayList<CreditInfo>(Math.max(customers.size(), products.size()));
        credits.forEach(credit -> {
            var creditDto = new CreditDto();
            creditDto.setCreditName(credit.getCreditName());
            var product = products.get(credit.getId());
            var customer = customers.get(credit.getId());
            var creditInfo = new CreditInfo();
            creditInfo.setCredit(creditDto);
            creditInfo.setCustomer(customer);
            creditInfo.setProduct(product);
            list.add(creditInfo);
        });
        return new CreditInfoList(list);
    }

    public HashMap<Long, ProductDto> createProductsMap(ProductDtoList products) {
        var productsMap = new HashMap<Long, ProductDto>();
        products.getProducts().forEach(productInfoDto ->
                productsMap.put(productInfoDto.getCreditNumber(), productInfoDto.getProduct()));
        return productsMap;
    }

    public HashMap<Long, CustomerDto> createCustomersMap(CustomerDtoList customers) {
        var customersMap = new HashMap<Long, CustomerDto>();
        customers.getCustomers().forEach(customerInfoDto ->
                customersMap.put(customerInfoDto.getCreditNumber(), customerInfoDto.getCustomer()));
        return customersMap;
    }
}

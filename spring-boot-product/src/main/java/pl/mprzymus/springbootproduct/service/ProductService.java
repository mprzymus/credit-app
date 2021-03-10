package pl.mprzymus.springbootproduct.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mprzymus.springbootproduct.mapper.ProductMapper;
import pl.mprzymus.springbootproduct.model.Product;
import pl.mprzymus.springbootproduct.model.ProductInfoDto;
import pl.mprzymus.springbootproduct.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Product saveIfExists(ProductInfoDto product) {
        var foundOptional = productRepository.findByProductNameAndCreditId
                (product.getProduct().getProductName(), product.getCreditNumber());
        if (foundOptional.isEmpty()) {
            var toSave = productMapper.productInfoDtoToProduct(product);
            return productRepository.save(toSave);
        }
        else {
            return updateValue(product, foundOptional.get());
        }
    }

    private Product updateValue(ProductInfoDto product, Product found) {
        found.setValue(product.getProduct().getValue());
        return productRepository.save(found);
    }
}

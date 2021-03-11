package pl.mprzymus.springbootproduct.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mprzymus.springbootproduct.mapper.ProductMapper;
import pl.mprzymus.springbootproduct.model.Product;
import pl.mprzymus.springbootproduct.model.ProductDtoList;
import pl.mprzymus.springbootproduct.model.ProductInfoDto;
import pl.mprzymus.springbootproduct.repository.ProductRepository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    public ProductDtoList findAll() {
        var allIterable = productRepository.findAll();
        var allList = StreamSupport.stream(allIterable.spliterator(), false)
                .map(product -> {
                    var dtoInfo = new ProductInfoDto();
                    dtoInfo.setCreditNumber(product.getCreditId());
                    dtoInfo.setProduct(productMapper.productToProductDto(product));
                    return dtoInfo;
                })
                .collect(Collectors.toList());
        var productDtoList = new ProductDtoList();
        productDtoList.setProducts(allList);
        return productDtoList;
    }
}

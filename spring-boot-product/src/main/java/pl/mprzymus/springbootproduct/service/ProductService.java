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

    public ProductInfoDto saveProduct(ProductInfoDto product) {
        var toSave = productMapper.productInfoDtoToProduct(product);
        var saved = productRepository.save(toSave);
        var dto = productMapper.productToProductDto(saved);
        var toReturn = new ProductInfoDto();
        toReturn.setCreditNumber(saved.getCreditId());
        toReturn.setProduct(dto);
        return toReturn;
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

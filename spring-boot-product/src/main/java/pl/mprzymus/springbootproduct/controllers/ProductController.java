package pl.mprzymus.springbootproduct.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mprzymus.springbootproduct.mapper.ProductMapper;
import pl.mprzymus.springbootproduct.model.ProductInfoDto;
import pl.mprzymus.springbootproduct.repository.ProductRepository;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {

    private final ProductMapper productMapper = ProductMapper.INSTANCE;
    private final ProductRepository productRepository;

    @PostMapping
    public ProductInfoDto createProduct(@Valid @RequestBody ProductInfoDto productInfoDto) {
        var product = productMapper.productInfoDtoToProduct(productInfoDto);
        var saved= productRepository.save(product);
        var dto = productMapper.productToProductDto(saved);
        var toReturn = new ProductInfoDto();
        toReturn.setCreditNumber(saved.getCreditId());
        toReturn.setProduct(dto);
        return toReturn;
    }
}

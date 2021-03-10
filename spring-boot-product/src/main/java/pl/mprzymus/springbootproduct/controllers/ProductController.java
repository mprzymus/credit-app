package pl.mprzymus.springbootproduct.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mprzymus.springbootproduct.mapper.ProductMapper;
import pl.mprzymus.springbootproduct.model.ProductInfoDto;
import pl.mprzymus.springbootproduct.service.ProductService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {

    private final ProductMapper productMapper = ProductMapper.INSTANCE;
    private final ProductService productService;

    @PostMapping
    public ProductInfoDto createProduct(@Valid @RequestBody ProductInfoDto productInfoDto) {
        var saved = productService.saveIfExists(productInfoDto);
        var dto = productMapper.productToProductDto(saved);
        var toReturn = new ProductInfoDto();
        toReturn.setCreditNumber(saved.getCreditId());
        toReturn.setProduct(dto);
        return toReturn;
    }
}

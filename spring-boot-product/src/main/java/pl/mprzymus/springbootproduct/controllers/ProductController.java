package pl.mprzymus.springbootproduct.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.mprzymus.springbootproduct.mapper.ProductMapper;
import pl.mprzymus.springbootproduct.model.ProductDtoList;
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
        return productService.saveProduct(productInfoDto);

    }

    @GetMapping
    public ProductDtoList getProducts() {
        return productService.findAll();
    }
}

package pl.mprzymus.springbootproduct.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.mprzymus.springbootproduct.model.Product;
import pl.mprzymus.springbootproduct.model.ProductDto;
import pl.mprzymus.springbootproduct.model.ProductInfoDto;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "value", source = "product.value")
    @Mapping(target = "productName", source = "product.productName")
    @Mapping(target = "creditId", source = "creditNumber")
    Product productInfoDtoToProduct(ProductInfoDto productInfoDto);

    ProductDto productToProductDto(Product product);
}

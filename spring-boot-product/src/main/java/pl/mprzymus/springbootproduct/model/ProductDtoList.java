package pl.mprzymus.springbootproduct.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductDtoList {
    private List<ProductInfoDto> products;
}

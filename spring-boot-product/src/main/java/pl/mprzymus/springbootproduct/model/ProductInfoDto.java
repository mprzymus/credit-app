package pl.mprzymus.springbootproduct.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ProductInfoDto {

    @NotNull
    @Valid
    private ProductDto product;

    @NotNull
    private Long creditNumber;
}

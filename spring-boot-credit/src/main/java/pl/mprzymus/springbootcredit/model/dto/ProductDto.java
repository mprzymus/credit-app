package pl.mprzymus.springbootcredit.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class ProductDto {
    @NotBlank
    private String productName;

    @Min(0)
    private Integer value;
}

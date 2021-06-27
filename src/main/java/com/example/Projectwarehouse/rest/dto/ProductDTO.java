package com.example.Projectwarehouse.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @Schema(description = "id of existing product", example = "1")
    private long id;
    @Schema(description = "product name", example = "Apple 12pro", required = true)
    @NotNull(message = "not be null")
    @NotBlank(message = "not be blank")
    private String name;
    @Schema(description = "quantity products in warehouse",example = "123",required = true)
    @NotNull(message = "not be null")
    @Min(0)
    private int quantity;
}

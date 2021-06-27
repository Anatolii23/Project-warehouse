package com.example.Projectwarehouse.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDTO {
    @Schema(description = "id of existing warehouse", example = "1")
    private long id;
    @Schema(description = "warehouse name", example = "Safety warehouse", required = true)
    @NotNull(message = "not be null")
    @NotBlank(message = "not be blank")
    private String name;
    @Schema(description = "warehouse address",example = "Poznan Poland",required = true)
    @NotNull(message = "not be null")
    @NotBlank(message = "not be blank")
    private String address;

}

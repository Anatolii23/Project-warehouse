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
public class ClientDTO {
    @Schema(description = "id of existing client", example = "1")
    private long id;
    @Schema(description = "client name", example = "John Smith", required = true)
    @NotNull(message = "not be null")
    @NotBlank(message = "not be blank")
    private String name;
    @Schema(description = "client address",example = "Poznan Poland",required = true)
    @NotNull(message = "not be null")
    @NotBlank(message = "not be blank")
    private String address;
    @Schema(description = "client credit limit",example = "10000",required = true)
    @NotNull(message = "not be null")
    @Min(0)
    private Double creditLimit;
}

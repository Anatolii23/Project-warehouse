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
public class EmployeeDTO {
    @Schema(description = "id of existing employee", example = "1")
    private long id;
    @Schema(description = "employee name", example = "John Smith", required = true)
    @NotNull(message = "not be null")
    @NotBlank(message = "not be blank")
    private String name;
    @Schema(description = "employee type",example = "Worker",required = true)
    @NotNull(message = "not be null")
    @NotBlank(message = "not be blank")
    private String type;
    @Schema(description = "employee address",example = "Poznan Poland",required = true)
    @NotNull(message = "not be null")
    @NotBlank(message = "not be blank")
    private String address;
    @Schema(description = "employee salary",example = "2000",required = true)
    @NotNull(message = "not be null")
    @Min(0)
    private int salary;
    @Schema(description = "warehouse where the employee works",required = true)
    @NotNull(message = "not be null")
    private WarehouseDTO warehouseDTO;
}

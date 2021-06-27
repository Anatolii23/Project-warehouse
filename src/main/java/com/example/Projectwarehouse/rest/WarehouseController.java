package com.example.Projectwarehouse.rest;

import com.example.Projectwarehouse.rest.dto.WarehouseDTO;
import com.example.Projectwarehouse.services.WarehouseServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.Projectwarehouse.config.SecurityConfig.ADMIN_ROLE;
import static com.example.Projectwarehouse.config.SecurityConfig.EMPLOYEE_ROLE;

@RestController
@RequiredArgsConstructor
public class WarehouseController {
    private final WarehouseServices warehouseServices;

    @PreAuthorize("hasRole('" + EMPLOYEE_ROLE + "')")
    @GetMapping("/findWarehouse")
    @Operation(description = "find warehouse by parameters")
    public List<WarehouseDTO> getWarehouseByParam(@Parameter(description = "id of warehouse")
                                                  @RequestParam(name = "id", required = false) Long id,
                                                  @Parameter(description = "name of warehouse")
                                                  @RequestParam(name = "name",required = false) String name,
                                                  @Parameter(description = "address of warehouse")
                                                  @RequestParam(name = "address",required = false) String address) {
        return warehouseServices.findWarehouseByParam(id, name, address);
    }

    @PreAuthorize("hasRole('" + EMPLOYEE_ROLE + "')")
    @PutMapping("/editWarehouse")
    @Operation(description = "edit warehouse")
    public ResponseEntity<WarehouseDTO> editWarehouse(@Parameter(description = "id of warehouse")
                                                      @RequestParam(name = "id") Long id,
                                                      @Valid @RequestBody WarehouseDTO warehouseDTO) {
        warehouseServices.editWarehouse(id, warehouseDTO);
        return ResponseEntity.ok(warehouseDTO);
    }

    @PreAuthorize("hasRole('" + EMPLOYEE_ROLE + "')")
    @PostMapping("/addWarehouse")
    @Operation(description = "add warehouse")
    public ResponseEntity<WarehouseDTO> addWarehouse(@Valid @RequestBody WarehouseDTO warehouseDTO) {
        warehouseServices.addWarehouse(warehouseDTO);
        return ResponseEntity.ok(warehouseDTO);
    }

    @PreAuthorize("hasRole('" + ADMIN_ROLE + "')")
    @DeleteMapping("/deleteWarehouse")
    @Operation(description = "find product by parameters")
    public ResponseEntity<WarehouseDTO> deleteWarehouse(@Parameter(description = "id of warehouse whose you delete")
                                @RequestParam(name = "id") Long id) {
        warehouseServices.deleteWarehouse(id);
        return ResponseEntity.ok().build();
    }
}

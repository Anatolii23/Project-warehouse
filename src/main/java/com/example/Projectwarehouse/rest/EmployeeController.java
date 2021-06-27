package com.example.Projectwarehouse.rest;

import com.example.Projectwarehouse.rest.dto.EmployeeDTO;
import com.example.Projectwarehouse.services.EmployeeServices;
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
public class EmployeeController {
    private final EmployeeServices employeeServices;

    @PreAuthorize("hasRole('" + ADMIN_ROLE + "')")
    @PostMapping("/addEmployee")
    @Operation(description = "add employee to data base")
    public ResponseEntity<EmployeeDTO> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        employeeServices.addEmployee(employeeDTO);
        return ResponseEntity.ok(employeeDTO);
    }

    @PreAuthorize("hasRole('" + ADMIN_ROLE + "')")
    @DeleteMapping("/removeEmployee")
    @Operation(description = "delete employee")
    public ResponseEntity<EmployeeDTO> removeEmployee(@Parameter(description = "the id of employee whose remove")
                                                      @RequestParam(name = "id") Long id) {
        employeeServices.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('" + ADMIN_ROLE + "')")
    @PutMapping("/editEmployee")
    @Operation(description = "edit employee")
    public EmployeeDTO editEmployee(@Parameter(description = "the id of employee whose change")
                                    @RequestParam(name = "id") Long id,
                                    @Valid @RequestBody EmployeeDTO employeeDTO) {
        return employeeServices.editEmployee(id, employeeDTO);
    }

    @PreAuthorize("hasRole('" + EMPLOYEE_ROLE + "')")
    @GetMapping("/getEmployee")
    @Operation(description = "find employee by parametrs")
    public List<EmployeeDTO> getEmployeeByParam(@Parameter(description = "the id of employee whose find")
                                                @RequestParam(name = "id") Long id,
                                                @Parameter(description = "the name of employee whose find")
                                                @RequestParam(name = "name") String name,
                                                @Parameter(description = "the type of employee whose find")
                                                @RequestParam(name = "type") String type,
                                                @Parameter(description = "the address of employee whose find")
                                                @RequestParam(name = "address") String address,
                                                @Parameter(description = "min salary of employee whose find")
                                                @RequestParam(name = "minSalary") Integer minSalary,
                                                @Parameter(description = "the max salary of employee whose find")
                                                @RequestParam(name = "maxSalary") Integer maxSalary) {
        return employeeServices.findEmployeeByParam(id, name, type, address, minSalary, maxSalary);
    }
}

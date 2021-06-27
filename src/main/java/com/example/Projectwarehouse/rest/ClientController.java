package com.example.Projectwarehouse.rest;

import com.example.Projectwarehouse.rest.dto.ClientDTO;
import com.example.Projectwarehouse.services.ClientServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.Projectwarehouse.config.SecurityConfig.EMPLOYEE_ROLE;

@RequiredArgsConstructor
@RestController
public class ClientController {
    private final ClientServices services;

    @PreAuthorize("hasRole('" + EMPLOYEE_ROLE + "')")
    @GetMapping("/getClient")
    @Operation(description = "find client by parameters")
    public List<ClientDTO> getClientsByParam(@Parameter(description = "id of client whose find")
                                             @RequestParam(name = "id", required = false) Long id,
                                             @Parameter(description = "name of client whose find")
                                             @RequestParam(name = "name", required = false) String name,
                                             @Parameter(description = "address of client whose find")
                                             @RequestParam(name = "address", required = false) String address) {
        return services.findClientByParam(id, name, address);
    }

    @PreAuthorize("hasRole('" + EMPLOYEE_ROLE + "')")
    @PostMapping("/addclient")
    @Operation(description = "add Client to data base ")
    public ResponseEntity<ClientDTO> addClient(@Valid @RequestBody ClientDTO clientDTO) {
        services.addClient(clientDTO);
        return ResponseEntity.ok(clientDTO);
    }

    @PreAuthorize("hasRole('" + EMPLOYEE_ROLE + "')")
    @DeleteMapping("/removeclient")
    @Operation(description = "remove client ")
    private ResponseEntity<ClientDTO> removeClient(@Parameter(description = "client id whose we delete")
                              @RequestParam(name = "id") Long id) {
        services.deleteClientById(id);
        return  ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('" + EMPLOYEE_ROLE + "')")
    @PutMapping("/editclient")
    @Operation(description = "edit client ")
    public ClientDTO editClient(@Parameter(description = "id of client whose change")
                                @RequestParam(name = "id") Long id,
                                @Valid @RequestBody ClientDTO clientDTO) {
        return services.editClient(id, clientDTO);
    }



}

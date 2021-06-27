package com.example.Projectwarehouse.rest;

import com.example.Projectwarehouse.rest.dto.OrderDTO;
import com.example.Projectwarehouse.services.OrderServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


import java.sql.Timestamp;
import java.util.List;

import static com.example.Projectwarehouse.config.SecurityConfig.USER_ROLE;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderServices orderServices;

    @PreAuthorize("hasRole('" + USER_ROLE + "')")
    @PostMapping("/addOrder")
    @Operation(description = "add order to data base")
    public ResponseEntity<OrderDTO> addOrder(@Valid @RequestBody OrderDTO orderDTO,
                                             @Parameter(description = "id of client")
                                             @RequestParam(name = "clientId") Long clientId,
                                             @Parameter(description = "productId whose order")
                                             @RequestParam(name = "productId") Long productId) {
        orderServices.addOrder(orderDTO, clientId, productId);
        return ResponseEntity.ok(orderDTO);
    }

    @PreAuthorize("hasRole('" + USER_ROLE + "')")
    @DeleteMapping("/removeorder")
    @Operation(description = "remove order by id")
    public ResponseEntity<OrderDTO> removeOrder(@Parameter(description = "id of order whose you remove")
                            @RequestParam(name = "id") Long id) {
        orderServices.removeOrderById(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('" + USER_ROLE + "')")
    @PutMapping("/changeorder")
    @Operation(description = "change details of order")
    public ResponseEntity<OrderDTO> changeOrder(@Parameter(description = "id of order whose you change")
                                                @RequestParam(name = "id") Long id,
                                                @Valid @RequestBody OrderDTO orderDTO) {
        orderServices.editOrder(id, orderDTO);
        return ResponseEntity.ok(orderDTO);
    }
    @PreAuthorize("hasRole('" + USER_ROLE + "')")
    @GetMapping("/getOrder")
    @Operation(description = "find order by parametrs")
    public List<OrderDTO> getOrderByParam(@Parameter(description = "id of order")
                                              @RequestParam(name = "id") Long id,
                                          @Parameter(description = "id of client")
                                          @RequestParam(name = "clientId") Long clientId,
                                          @Parameter(description = "id of product")
                                              @RequestParam(name = "productId") Long productId,
                                          @Parameter(description = "status of order")
                                              @RequestParam(name = "status") String status,
                                          @Parameter(description = "Max data of order whose you find")
                                              @RequestParam(name = "maxdata") Timestamp maxData,
                                          @Parameter(description = "Min data od order whose you find")
                                              @RequestParam(name = "mindata") Timestamp minData){
       return orderServices.findOrderByParam(id, clientId, productId, status, maxData, minData);
    }
}
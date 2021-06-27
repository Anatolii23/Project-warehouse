package com.example.Projectwarehouse.rest;

import com.example.Projectwarehouse.rest.dto.ProductDTO;
import com.example.Projectwarehouse.services.ProductServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.example.Projectwarehouse.config.SecurityConfig.EMPLOYEE_ROLE;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductServices productServices;

    @PreAuthorize("hasRole('" + EMPLOYEE_ROLE + "')")
    @PostMapping("/addproduct")
    @Operation(description = "add product to data base")
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO) {
        productServices.addProduct(productDTO);
        return ResponseEntity.ok(productDTO);
    }

    @PreAuthorize("hasRole('" + EMPLOYEE_ROLE + "')")
    @DeleteMapping("/deleteProduct")
    @Operation(description = "remove product")
    public ResponseEntity<ProductDTO> deleteProduct(@Parameter(description = "id of product whose you delete")
                              @RequestParam(name = "clientId") Long id) {
        productServices.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('" + EMPLOYEE_ROLE + "')")
    @PutMapping("/editProduct")
    @Operation(description = "edit this product")
    public ResponseEntity<ProductDTO> editProduct(@Parameter(description = "id of product")
                                                  @RequestParam(name = "id") Long id,
                                                  @Valid @RequestBody ProductDTO productDTO) {
        productServices.editProduct(id, productDTO);
        return ResponseEntity.ok(productDTO);
    }

    @PreAuthorize("hasRole('" + EMPLOYEE_ROLE + "')")
    @GetMapping("/findproduct")
    @Operation(description = "find product by parameters")
    public List<ProductDTO> getProductByParam(@Parameter(description = "id of product")
                                                        @RequestParam(name = "id") Long id,
                                              @Parameter(description = "name of product")
                                                        @RequestParam(name = "name") String name,
                                              @Parameter(description = "quantity of product")
                                                        @RequestParam(name = "quantity") Integer quantity) {
       return productServices.findProductByParam(id, name, quantity);

    }


}

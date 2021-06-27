package com.example.Projectwarehouse.services;

import com.example.Projectwarehouse.entity.ProductEntity;
import com.example.Projectwarehouse.errors.ProductNotFoundException;
import com.example.Projectwarehouse.repositories.ProductCache;
import com.example.Projectwarehouse.repositories.ProductRepository;
import com.example.Projectwarehouse.rest.dto.ProductDTO;
import com.example.Projectwarehouse.until.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServices {
    private final ProductRepository productRepository;
    private final ProductCache productCache;

    public ProductDTO addProduct(ProductDTO productDTO) {
        productCache.saveProductInCache(productDTO);
        productRepository.save(EntityDtoMapper.mappedToProductEntity(productDTO));
        return productDTO;
    }

    public void deleteProduct(Long id) {
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);
        if (optionalProductEntity.isPresent()) {
            productRepository.delete(optionalProductEntity.get());
            productCache.deleteProductFromCache(optionalProductEntity.get().getId());
        } else throw new ProductNotFoundException(id);
    }

    public ProductDTO editProduct(Long id, ProductDTO productDTO) {
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);
        if (optionalProductEntity.isPresent()) {
            ProductEntity productEntity = optionalProductEntity.get();
            BeanUtils.copyProperties(productDTO, productEntity);
            productRepository.save(productEntity);
            productCache.saveProductInCache(EntityDtoMapper.mappedToProductDTO(productEntity));
            return productDTO;
        } else throw new ProductNotFoundException(id);

    }
    public List<ProductDTO> findProductByParam(Long id,String name, Integer quantity ){
       return productRepository.findAll().stream()
               .filter(productEntity -> id==null || id==productEntity.getId())
               .filter(productEntity -> name==null || productEntity.getName().equals(name))
               .filter(productEntity -> quantity==null || productEntity.getQuantity()==quantity)
               .map(EntityDtoMapper::mappedToProductDTO)
               .collect(Collectors.toList());
    }

}

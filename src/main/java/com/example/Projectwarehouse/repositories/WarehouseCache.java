package com.example.Projectwarehouse.repositories;

import com.example.Projectwarehouse.config.CacheConfig;
import com.example.Projectwarehouse.rest.dto.WarehouseDTO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class WarehouseCache {
    @Cacheable(key = "#id", cacheManager = CacheConfig.Warehouse_DTO_Cache_Manager,
            cacheNames = CacheConfig.Warehouse_DTO_Cache_Name)
    public Optional<WarehouseDTO> getWarehouse(Long id) {
        return Optional.empty();
    }

    @CachePut(key = "#warehouseDTO.id", cacheManager = CacheConfig.Warehouse_DTO_Cache_Manager,
            cacheNames = CacheConfig.Warehouse_DTO_Cache_Name)
    public WarehouseDTO saveWarehouseInCache(WarehouseDTO warehouseDTO) {
        return warehouseDTO;
    }

    @CacheEvict(key = "#id", cacheManager = CacheConfig.Warehouse_DTO_Cache_Manager,
            cacheNames = CacheConfig.Warehouse_DTO_Cache_Name)
    public void deleteWarehouseFromCache(Long id) {
    }
}

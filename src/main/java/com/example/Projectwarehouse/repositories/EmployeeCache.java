package com.example.Projectwarehouse.repositories;

import com.example.Projectwarehouse.config.CacheConfig;
import com.example.Projectwarehouse.rest.dto.EmployeeDTO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EmployeeCache {
    @Cacheable(key = "#id", cacheManager = CacheConfig.Employee_DTO_Cache_Manager,
            cacheNames = CacheConfig.Employee_DTO_Cache_Name)
    public Optional<EmployeeDTO> getEmployee(Long id) {
        return Optional.empty();
    }

    @CachePut(key = "#employeeDTO.id", cacheManager = CacheConfig.Employee_DTO_Cache_Manager,
            cacheNames = CacheConfig.Employee_DTO_Cache_Name)
    public EmployeeDTO saveEmployeeInCache(EmployeeDTO employeeDTO) {
        return employeeDTO;
    }

    @CacheEvict(key = "#id", cacheManager = CacheConfig.Employee_DTO_Cache_Manager,
            cacheNames = CacheConfig.Employee_DTO_Cache_Name)
    public void deleteEmployeeFromCache(Long id) {
    }
}

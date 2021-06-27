package com.example.Projectwarehouse.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class CacheConfig {
    public static final String Client_DTO_Cache_Manager = "clientResponseCacheManager";
    public static final String Client_DTO_Cache_Name = "clientResponseCache";
    public static final String Product_DTO_Cache_Manager = "productResponseCacheManager";
    public static final String Product_DTO_Cache_Name = "productResponseCache";
    public static final String Employee_DTO_Cache_Manager = "EmployeeEntityCacheManager";
    public static final String Employee_DTO_Cache_Name = "EmployeeEntityResponseCache";
    public static final String Warehouse_DTO_Cache_Manager = "WarehouseEntityCacheManager";
    public static final String Warehouse_DTO_Cache_Name = "WarehouseEntityResponseCache";

    @Bean(Client_DTO_Cache_Manager)
    @Primary
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder().expireAfterWrite(120, TimeUnit.SECONDS);
        cacheManager.setCaffeine(caffeine);
        cacheManager.setCacheNames(Collections.singleton(Client_DTO_Cache_Name));
        return cacheManager;
    }

    @Bean(Product_DTO_Cache_Manager)
    public CacheManager cacheManager1() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder().expireAfterWrite(120, TimeUnit.SECONDS);
        cacheManager.setCaffeine(caffeine);
        cacheManager.setCacheNames(Collections.singleton(Product_DTO_Cache_Name));
        return cacheManager;
    }
    @Bean(Employee_DTO_Cache_Manager)
    public CacheManager cacheManager2() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder().expireAfterWrite(120, TimeUnit.SECONDS);
        cacheManager.setCaffeine(caffeine);
        cacheManager.setCacheNames(Collections.singleton(Employee_DTO_Cache_Name));
        return cacheManager;
    }
    @Bean(Warehouse_DTO_Cache_Manager)
    public CacheManager cacheManager3() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder().expireAfterWrite(120, TimeUnit.SECONDS);
        cacheManager.setCaffeine(caffeine);
        cacheManager.setCacheNames(Collections.singleton(Warehouse_DTO_Cache_Name));
        return cacheManager;
    }
}

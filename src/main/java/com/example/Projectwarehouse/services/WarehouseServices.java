package com.example.Projectwarehouse.services;

import com.example.Projectwarehouse.entity.WarehouseEntity;
import com.example.Projectwarehouse.errors.WarehouseNotFoundException;
import com.example.Projectwarehouse.repositories.WarehouseCache;
import com.example.Projectwarehouse.repositories.WarehouseRepository;
import com.example.Projectwarehouse.rest.dto.WarehouseDTO;
import com.example.Projectwarehouse.until.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WarehouseServices {
    private final WarehouseCache warehouseCache;
    private final WarehouseRepository warehouseRepository;

    public WarehouseDTO addWarehouse(WarehouseDTO warehouseDTO) {
        warehouseCache.saveWarehouseInCache(warehouseDTO);
        warehouseRepository.save(EntityDtoMapper.mappedToWarehouseEntity(warehouseDTO));
        return warehouseDTO;
    }

    public void deleteWarehouse(Long id) {
        Optional<WarehouseEntity> optionalWarehouseEntity = warehouseRepository.findById(id);
        if (optionalWarehouseEntity.isPresent()) {
            warehouseRepository.delete(optionalWarehouseEntity.get());
            warehouseCache.deleteWarehouseFromCache(optionalWarehouseEntity.get().getId());
        } else throw new WarehouseNotFoundException(id);
    }

    public WarehouseDTO editWarehouse(Long id, WarehouseDTO warehouseDTO) {
        Optional<WarehouseEntity> optionalWarehouseEntity = warehouseRepository.findById(id);
        if (optionalWarehouseEntity.isPresent()) {
            WarehouseEntity warehouseEntity = optionalWarehouseEntity.get();
            BeanUtils.copyProperties(warehouseDTO, warehouseEntity);
            warehouseRepository.save(warehouseEntity);
            warehouseCache.saveWarehouseInCache(EntityDtoMapper.mappedToWarehouseDTO(warehouseEntity));
            return warehouseDTO;
        } else throw new WarehouseNotFoundException(id);
    }

    public List<WarehouseDTO> findWarehouseByParam(Long id, String name, String address) {
       return warehouseRepository.findAll().stream()
                .filter(warehouseEntity -> id == null || id == warehouseEntity.getId())
                .filter(warehouseEntity -> name == null || name.equals(warehouseEntity.getName()))
                .filter(warehouseEntity -> address == null || address.equals(warehouseEntity.getAddress()))
                .map(EntityDtoMapper::mappedToWarehouseDTO)
                .collect(Collectors.toList());
    }
}

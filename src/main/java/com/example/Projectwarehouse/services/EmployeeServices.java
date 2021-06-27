package com.example.Projectwarehouse.services;

import com.example.Projectwarehouse.entity.EmployeeEntity;
import com.example.Projectwarehouse.entity.WarehouseEntity;
import com.example.Projectwarehouse.errors.EmployeeNotFoundException;
import com.example.Projectwarehouse.repositories.EmployeeCache;
import com.example.Projectwarehouse.repositories.EmployeeRepository;
import com.example.Projectwarehouse.repositories.WarehouseCache;
import com.example.Projectwarehouse.repositories.WarehouseRepository;
import com.example.Projectwarehouse.rest.dto.EmployeeDTO;
import com.example.Projectwarehouse.until.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServices {
    private final EmployeeCache employeeCache;
    private final EmployeeRepository employeeRepository;
    private final WarehouseRepository warehouseRepository;
    private final WarehouseCache warehouseCache;

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = EntityDtoMapper.mappedToEmployeeEntity(employeeDTO);
        WarehouseEntity warehouseEntity = EntityDtoMapper.mappedToWarehouseEntity(employeeDTO.getWarehouseDTO());
        Optional<WarehouseEntity> optionalWarehouseEntity = warehouseRepository.findById(employeeDTO.getWarehouseDTO().getId());
        if (optionalWarehouseEntity.isPresent()) {
            warehouseEntity = optionalWarehouseEntity.get();
        }
        employeeEntity.setWarehouseEntity(warehouseEntity);
        List<EmployeeEntity> employeeEntities = new ArrayList<>();
        employeeEntities.add(employeeEntity);
        warehouseEntity.setEmployeeEntityList(employeeEntities);
        warehouseRepository.save(warehouseEntity);
        warehouseCache.saveWarehouseInCache(EntityDtoMapper.mappedToWarehouseDTO(warehouseEntity));
        employeeCache.saveEmployeeInCache(EntityDtoMapper.mappedToEmployeeDTO(employeeEntity));
        employeeRepository.save(employeeEntity);
        return employeeDTO;
    }

    public void deleteEmployee(Long id) {
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(id);
        if (optionalEmployeeEntity.isPresent()) {
            employeeRepository.delete(optionalEmployeeEntity.get());
            employeeCache.deleteEmployeeFromCache(optionalEmployeeEntity.get().getId());
        } else throw new EmployeeNotFoundException(id);
    }

    public EmployeeDTO editEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(id);
        if (optionalEmployeeEntity.isPresent()) {
            EmployeeEntity employeeEntity = optionalEmployeeEntity.get();
            BeanUtils.copyProperties(employeeDTO, employeeEntity);
            employeeEntity.setWarehouseEntity(EntityDtoMapper.mappedToWarehouseEntity(employeeDTO.getWarehouseDTO()));
            employeeRepository.save(employeeEntity);
            employeeCache.saveEmployeeInCache(EntityDtoMapper.mappedToEmployeeDTO(employeeEntity));
            return employeeDTO;
        } else throw new EmployeeNotFoundException(id);
    }

    public List<EmployeeDTO> findEmployeeByParam(Long id, String name, String type, String address,
                                                 Integer minSalary, Integer maxSalary) {
        return employeeRepository.findAll().stream()
                .filter(employeeEntity ->  id == null || employeeEntity.getId() == id )
                .filter(employeeEntity -> employeeEntity.getAddress().equals(address) || address == null)
                .filter(employeeEntity -> employeeEntity.getName().equals(name) || name == null)
                .filter(employeeEntity -> employeeEntity.getType().equals(type) || type == null)
                .filter(employeeEntity -> minSalary == null || employeeEntity.getSalary() >= minSalary)
                .filter(employeeEntity -> maxSalary == null || employeeEntity.getSalary() <= maxSalary)
                .map(EntityDtoMapper::mappedToEmployeeDTO)
                .collect(Collectors.toList());
    }
}

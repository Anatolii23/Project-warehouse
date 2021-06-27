package com.example.Projectwarehouse.until;

import com.example.Projectwarehouse.entity.*;
import com.example.Projectwarehouse.rest.dto.*;
import org.springframework.beans.BeanUtils;

public class EntityDtoMapper {
    public static ClientDTO mappedToClientDTO(ClientEntity clientEntity) {
        ClientDTO clientDTO = ClientDTO.builder().build();
        BeanUtils.copyProperties(clientEntity, clientDTO);

        return clientDTO;
    }

    public static ClientEntity mappedToClientEntity(ClientDTO clientDTO) {
        ClientEntity clientEntity = new ClientEntity();
        BeanUtils.copyProperties(clientDTO, clientEntity);
        return clientEntity;
    }
    public static ProductDTO mappedToProductDTO(ProductEntity productEntity) {
        ProductDTO productDTO = ProductDTO.builder().build();
        BeanUtils.copyProperties(productEntity, productDTO);
        return productDTO;
    }

    public static ProductEntity mappedToProductEntity(ProductDTO productDTO) {
        ProductEntity productEntity = ProductEntity.builder().build();
        BeanUtils.copyProperties(productDTO, productEntity);
        return productEntity;
    }
    public static OrderDTO mappedToOrderDTO(OrderEntity orderEntity) {
        OrderDTO orderDTO = OrderDTO.builder().build();
        BeanUtils.copyProperties(orderEntity, orderDTO);
        return orderDTO;
    }

    public static OrderEntity mappedToOrderEntity(OrderDTO orderDTO) {
        OrderEntity orderEntity = OrderEntity.builder().build();
        BeanUtils.copyProperties(orderDTO, orderEntity);
        return orderEntity;
    }
    public static EmployeeDTO mappedToEmployeeDTO(EmployeeEntity employeeEntity) {
        EmployeeDTO employeeDTO = EmployeeDTO.builder().build();
        BeanUtils.copyProperties(employeeEntity, employeeDTO);
        if(employeeEntity.getWarehouseEntity()!=null)
        {
            employeeDTO.setWarehouseDTO(mappedToWarehouseDTO(employeeEntity.getWarehouseEntity()));
        }
        return employeeDTO;
    }

    public static EmployeeEntity mappedToEmployeeEntity(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = EmployeeEntity.builder().build();
        BeanUtils.copyProperties(employeeDTO, employeeEntity);
        if(employeeDTO.getWarehouseDTO()!=null)
        {
            employeeEntity.setWarehouseEntity(mappedToWarehouseEntity(employeeDTO.getWarehouseDTO()));
        }
        return employeeEntity;
    }
    public static WarehouseDTO mappedToWarehouseDTO(WarehouseEntity warehouseEntity) {
        WarehouseDTO warehouseDTO = WarehouseDTO.builder().build();
        BeanUtils.copyProperties(warehouseEntity, warehouseDTO);
        return warehouseDTO;
    }

    public static WarehouseEntity mappedToWarehouseEntity(WarehouseDTO warehouseDTO) {
        WarehouseEntity warehouseEntity = WarehouseEntity.builder().build();
        BeanUtils.copyProperties(warehouseDTO, warehouseEntity);
        return warehouseEntity;
    }
}

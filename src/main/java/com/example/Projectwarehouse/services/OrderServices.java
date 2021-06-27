package com.example.Projectwarehouse.services;

import com.example.Projectwarehouse.entity.ClientEntity;
import com.example.Projectwarehouse.entity.OrderEntity;
import com.example.Projectwarehouse.entity.ProductEntity;
import com.example.Projectwarehouse.errors.ClientNotFoundException;
import com.example.Projectwarehouse.errors.OrderNotFoundException;
import com.example.Projectwarehouse.errors.ProductNotFoundException;
import com.example.Projectwarehouse.repositories.ClientRepository;
import com.example.Projectwarehouse.repositories.OrderRepository;
import com.example.Projectwarehouse.repositories.ProductRepository;
import com.example.Projectwarehouse.rest.dto.OrderDTO;
import com.example.Projectwarehouse.until.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderServices {
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderDTO addOrder(OrderDTO orderDTO, Long clientId, Long productId) {
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        ClientEntity clientEntity = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));
        OrderEntity orderEntity = EntityDtoMapper.mappedToOrderEntity(orderDTO);
        orderEntity.setClientEntity(clientEntity);
        orderEntity.setProductEntity(productEntity);
        orderRepository.save(orderEntity);
        productRepository.save(productEntity);
        clientRepository.save(clientEntity);
        return orderDTO;
    }

    public void removeOrderById(Long id) {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);
        if (orderEntityOptional.isPresent()) {
            orderRepository.delete(orderEntityOptional.get());
        } else throw new OrderNotFoundException(id);
    }

    public OrderDTO editOrder(Long id, OrderDTO orderDTO) {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);
        if (orderEntityOptional.isPresent()) {
            BeanUtils.copyProperties(orderDTO, orderEntityOptional.get());
            return orderDTO;
        } else throw new OrderNotFoundException(id);

    }

    public List<OrderDTO> findOrderByParam(Long id, Long clientId, Long productId, String status,
                                           Timestamp maxData, Timestamp minData) {
        return orderRepository.findAll().stream()
                .filter(orderEntity -> id == null || id == orderEntity.getId())
                .filter(orderEntity -> status == null || status.equals(orderEntity.getStatus().toString()))
                .filter(orderEntity -> minData == null || minData.after(orderEntity.getData()))
                .filter(orderEntity -> maxData == null || maxData.before(orderEntity.getData()))
                .filter(orderEntity -> clientId == null || clientId == orderEntity.getClientEntity().getId())
                .filter(orderEntity -> productId == null || productId == orderEntity.getProductEntity().getId())
                .map(EntityDtoMapper::mappedToOrderDTO)
                .collect(Collectors.toList());
    }

}


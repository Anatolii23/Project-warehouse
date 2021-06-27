package com.example.Projectwarehouse.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "product")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "quantity")
    private int quantity;
    @OneToMany(mappedBy = "productEntity")
    private List<OrderEntity> orderEntityList;
    @ManyToMany(mappedBy = "productEntityList")
    private List<WarehouseEntity> warehouseEntityList;
}

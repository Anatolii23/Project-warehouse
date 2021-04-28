package com.example.Projectwarehouse.DataBaseOnHibernate;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "product")
@Entity
@Getter
@Setter
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
    @ManyToMany
    @JoinTable(name = "orderoption",
            joinColumns = @JoinColumn(name = "warehouseId"),
            inverseJoinColumns = @JoinColumn(name = "productId"))
    private List<WarehouseEntity> warehouseEntityList;
    public ProductEntity(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public ProductEntity() {
    }
}

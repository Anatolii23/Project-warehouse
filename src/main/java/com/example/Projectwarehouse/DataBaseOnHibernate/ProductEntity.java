package com.example.Projectwarehouse.DataBaseOnHibernate;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "product")
@Entity
@Data
@NoArgsConstructor
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

    public ProductEntity(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

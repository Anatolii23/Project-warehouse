package com.example.Projectwarehouse.DataBaseOnHibernate;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "warehouse")
@Entity
@Getter
@Setter
public class WarehouseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "town")
    private String town;
    @ManyToMany(mappedBy = "warehouseEntityList")
    private List<ProductEntity> productEntityList;
    @OneToMany(mappedBy = "warehouseEntity")
    private List<EmployeeEntity> employeeEntityList;

    public WarehouseEntity() {
    }

    public WarehouseEntity(String name, String town) {
        this.name = name;
        this.town = town;
    }
}

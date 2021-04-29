package com.example.Projectwarehouse.DataBaseOnHibernate;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "warehouse")
@Entity
@Data
@NoArgsConstructor
public class WarehouseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "town")
    private String town;
    @ManyToMany
    @JoinTable(name = "orderoption",
            joinColumns = @JoinColumn(name = "warehouseId"),
            inverseJoinColumns = @JoinColumn(name = "productId"))
    private List<ProductEntity> productEntityList;
    @OneToMany(mappedBy = "warehouseEntity")
    private List<EmployeeEntity> employeeEntityList;
    public WarehouseEntity(String name, String town) {
        this.name = name;
        this.town = town;
    }

    @Override
    public String toString() {
        return "WarehouseEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", town='" + town + '\'' +
                '}';
    }
}

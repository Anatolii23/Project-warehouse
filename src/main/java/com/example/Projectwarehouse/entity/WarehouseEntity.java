package com.example.Projectwarehouse.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "warehouse", schema = "project_warehouse")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class WarehouseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @ManyToMany
    @JoinTable(name = "orderoption",
            joinColumns = @JoinColumn(name = "warehouseid"),
            inverseJoinColumns = @JoinColumn(name = "productid"))
    private List<ProductEntity> productEntityList;
    @OneToMany(mappedBy = "warehouseEntity" , cascade = CascadeType.ALL)
    private List<EmployeeEntity> employeeEntityList;
}

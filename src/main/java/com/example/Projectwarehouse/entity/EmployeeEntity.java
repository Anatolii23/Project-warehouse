package com.example.Projectwarehouse.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "employee" , schema = "project_warehouse")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "address")
    private String address;
    @Column(name = "salary")
    private int salary;
    @ManyToOne
    @JoinColumn(name = "warehouseid")
    private WarehouseEntity warehouseEntity;
}

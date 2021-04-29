package com.example.Projectwarehouse.DataBaseOnHibernate;

import lombok.*;

import javax.persistence.*;

@Table(name = "employee")
@Entity
@Data
@NoArgsConstructor
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
    @JoinColumn(name = "warehouseId")
    private WarehouseEntity warehouseEntity;
    public EmployeeEntity(String name, String type, String address, int salary) {
        this.name = name;
        this.type = type;
        this.address = address;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                '}';
    }
}

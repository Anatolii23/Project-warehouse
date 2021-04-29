package com.example.Projectwarehouse.DataBaseOnHibernate;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "client")
@Entity
@Data
@NoArgsConstructor
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "creditLimit")
    private int creditLimit;
    @OneToMany(mappedBy = "clientEntity")
    private List<OrderEntity> orderEntityList;
    public ClientEntity(String name, String address, int creditLimit) {
        this.name = name;
        this.address = address;
        this.creditLimit = creditLimit;

    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", creditLimit=" + creditLimit +
                '}';
    }
}

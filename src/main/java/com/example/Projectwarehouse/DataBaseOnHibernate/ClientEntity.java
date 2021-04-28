package com.example.Projectwarehouse.DataBaseOnHibernate;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "client")
@Entity
@Getter
@Setter
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

    public ClientEntity() {
    }

    public ClientEntity(String name, String address, int creditLimit) {
        this.name = name;
        this.address = address;
        this.creditLimit = creditLimit;
    }
}

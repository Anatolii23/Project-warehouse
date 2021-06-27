package com.example.Projectwarehouse.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "client")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
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
    private Double creditLimit;
    @OneToMany(mappedBy = "clientEntity" , cascade = CascadeType.ALL)
    private List<OrderEntity> orderEntityList;
}

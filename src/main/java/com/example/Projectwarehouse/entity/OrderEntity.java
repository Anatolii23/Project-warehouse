package com.example.Projectwarehouse.entity;

import com.example.Projectwarehouse.repositories.StatusRepository;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "orders", schema = "project_warehouse")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "status")
    private StatusRepository status;
    @Column(name = "data")
    private Timestamp data;
    @ManyToOne
    @JoinColumn(name = "clientid")
    private ClientEntity clientEntity;
    @ManyToOne
    @JoinColumn(name = "productid")
    private ProductEntity productEntity;
}

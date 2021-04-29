package com.example.Projectwarehouse.DataBaseOnHibernate;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "orders")
@Entity
@Data
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "status")
    private String status;
    @Column(name = "data")
    private Timestamp data;
    @ManyToOne
    @JoinColumn(name = "clientId")
    private ClientEntity clientEntity;
    @ManyToOne
    @JoinColumn(name = "productId")
    private ProductEntity productEntity;
    public OrderEntity(String status, Timestamp data) {
        this.status = status;
        this.data = data;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}

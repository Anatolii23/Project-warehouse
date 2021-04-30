package com.example.Projectwarehouse;

import com.example.Projectwarehouse.DataBaseOnHibernate.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@SpringBootApplication
public class ProjectWarehouseApplication {


    public static void main(String[] args) {
//		SpringApplication.run(ProjectWarehouseApplication.class, args);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("project_warehouse");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<EmployeeEntity> employeeEntityList = entityManager.createQuery("select e from EmployeeEntity e ", EmployeeEntity.class)
                .getResultList();
        List<WarehouseEntity> warehouseEntityList = entityManager.createQuery("select w from WarehouseEntity w", WarehouseEntity.class)
                .getResultList();
        List<ProductEntity> productEntityList = entityManager.createQuery("SELECT p from ProductEntity p", ProductEntity.class)
                .getResultList();
        List<ClientEntity> clientEntityList = entityManager.createQuery("SELECT c from ClientEntity c", ClientEntity.class)
                .getResultList();
        List<OrderEntity> orderEntityList = entityManager.createQuery("select o from OrderEntity o", OrderEntity.class)
                .getResultList();
        System.out.println("\tWszyscy pracownicy");
        employeeEntityList.forEach(System.out::println);
        System.out.println("\t wszyscy magazyny");
        warehouseEntityList.forEach(System.out::println);
        System.out.println("\t wszyscy producty");
        productEntityList.forEach(System.out::println);
        System.out.println("\t wszyscy clienty");
        clientEntityList.forEach(System.out::println);
        System.out.println("\t wszyscy zamowienia");
        orderEntityList.forEach(System.out::println);

        entityManager.close();
        entityManagerFactory.close();

    }

}
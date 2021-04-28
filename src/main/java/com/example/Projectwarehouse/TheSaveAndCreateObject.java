package com.example.Projectwarehouse;

import com.example.Projectwarehouse.DataBaseOnHibernate.*;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


public class TheSaveAndCreateObject {
    public static void addProductToWarehouse(EntityManager entityManager, List<ProductEntity> products,
                                             List<WarehouseEntity> warehouses){
        List<WarehouseEntity> warehouseFirst = warehouses.subList(0,1);
        List<WarehouseEntity> warehouseSecond = warehouses.subList(1,2);
        List<ProductEntity> firstProducts = products;
        List<ProductEntity> secondProducts=products.subList(0,3);
        warehouseFirst.forEach(warehouseEntity -> warehouseEntity.setProductEntityList(firstProducts));
        firstProducts.forEach(productEntity -> productEntity.setWarehouseEntityList(warehouseFirst));
        warehouseSecond.forEach(warehouseEntity -> warehouseEntity.setProductEntityList(secondProducts));
        secondProducts.forEach(productEntity -> productEntity.setWarehouseEntityList(warehouseSecond));
        entityManager.getTransaction().begin();
        warehouseFirst.forEach(entityManager::persist);
        warehouseSecond.forEach(entityManager::persist);
        firstProducts.forEach(entityManager::persist);
        secondProducts.forEach(entityManager::persist);
        entityManager.getTransaction().commit();
    }
    public static  OrderEntity createOrder(EntityManager entityManager, ClientEntity clientEntity, ProductEntity productEntity){

      OrderEntity order1=new OrderEntity("InProcess", Timestamp.valueOf("2020-03-02 04:33:17"));
       order1.setProductEntity(productEntity);
       order1.setClientEntity(clientEntity);
        entityManager.getTransaction().begin();
        entityManager.persist(order1);
        entityManager.persist(productEntity);
        entityManager.persist(clientEntity);
        entityManager.getTransaction().commit();
        return order1;
    }
    public static void addEmployeeToWarehouse(EntityManager entityManager, List<EmployeeEntity> employees,
                                              List<WarehouseEntity> warehouses) {
        WarehouseEntity firstWarehouse = warehouses.get(0);
        WarehouseEntity secondWarehouse=warehouses.get(1);
        List<EmployeeEntity> firstEmployee = employees.subList(0, 3);
        List<EmployeeEntity> secondEmployee = employees.subList(3, 6);
        firstWarehouse.setEmployeeEntityList(new ArrayList<>(firstEmployee));
        firstEmployee.forEach(employeeEntity -> employeeEntity.setWarehouseEntity(firstWarehouse));
        secondWarehouse.setEmployeeEntityList(new ArrayList<>(secondEmployee));
        secondEmployee.forEach(employeeEntity -> employeeEntity.setWarehouseEntity(secondWarehouse));
        entityManager.getTransaction().begin();
        entityManager.persist(firstWarehouse);
        firstEmployee.forEach(entityManager::persist);
        entityManager.persist(secondWarehouse);
        secondEmployee.forEach(entityManager::persist);
        entityManager.getTransaction().commit();
    }

    public static List<ClientEntity> createAndSaveClient(EntityManager entityManager) {
        List<ClientEntity> clientEntities = new ArrayList<>();
        clientEntities.add(new ClientEntity("Artur", "Poznan", 10000));
        clientEntities.add(new ClientEntity("Kasia", "Poznan", 2000));
        clientEntities.add(new ClientEntity("Daniel", "Warsaw", 0));
        clientEntities.add(new ClientEntity("Piotr", "Warsaw", 4000));
        clientEntities.add(new ClientEntity("Michal", "Poznan", 5000));
        entityManager.getTransaction().begin();
        clientEntities.forEach(entityManager::persist);
        entityManager.getTransaction().commit();
        return clientEntities;
    }

    public static List<EmployeeEntity> createAndSaveEmployee(EntityManager entityManager) {
        List<EmployeeEntity> employeeEntities = new ArrayList<>();
        employeeEntities.add(new EmployeeEntity("Cyryl", "Director", "Warsaw", 22000));
        employeeEntities.add(new EmployeeEntity("Natalia", "Administrator", "Warsaw", 6000));
        employeeEntities.add(new EmployeeEntity("Michal", "Worker", "Warsaw", 5000));
        employeeEntities.add(new EmployeeEntity("Damian", "Director", "Poznan", 20000));
        employeeEntities.add(new EmployeeEntity("Martyna", "Administrator", "Poznan", 6000));
        employeeEntities.add(new EmployeeEntity("Sebastian", "Worker", "Poznan", 4500));
        entityManager.getTransaction().begin();
        employeeEntities.forEach(entityManager::persist);
        entityManager.getTransaction().commit();
        return employeeEntities;
    }

    public static List<WarehouseEntity> createAndSaveWarehouse(EntityManager entityManager) {
        List<WarehouseEntity> warehouseEntities = new ArrayList<>();
        warehouseEntities.add(new WarehouseEntity("FirstWarehouse", "Warsaw"));
        warehouseEntities.add(new WarehouseEntity("SecondWarehouse", "Poznan"));
        entityManager.getTransaction().begin();
        warehouseEntities.forEach(entityManager::persist);
        entityManager.getTransaction().commit();
        return warehouseEntities;
    }

    public static List<ProductEntity> createAndSaveProduct(EntityManager entityManager) {
        List<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(new ProductEntity("Computer", 324));
        productEntityList.add(new ProductEntity("Laptop", 667));
        productEntityList.add(new ProductEntity("AirPods", 456));
        productEntityList.add(new ProductEntity("TV", 34));
        productEntityList.add(new ProductEntity("Smartphone", 234));
        entityManager.getTransaction().begin();
        productEntityList.forEach(entityManager::persist);
        entityManager.getTransaction().commit();
        return productEntityList;
    }

}

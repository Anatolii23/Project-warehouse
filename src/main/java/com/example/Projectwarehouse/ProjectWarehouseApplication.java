package com.example.Projectwarehouse;

import com.example.Projectwarehouse.DataBaseOnHibernate.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.stream.Collectors;

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
		TheSaveAndCreateObject.addProductToWarehouse(entityManager,productEntityList,warehouseEntityList);//Cannot add or update a child row:
		// a foreign key constraint fails (`project_warehouse`.`orderoption`, CONSTRAINT `fk_orderOption_warehouse_id`
		// FOREIGN KEY (`warehouseId`) REFERENCES `warehouse` (`id`))
		entityManager.close();
		entityManagerFactory.close();
	}

}

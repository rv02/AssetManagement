package com.rv02.AssetManagement;

import com.rv02.AssetManagement.dao.AssetRepository;
import com.rv02.AssetManagement.dao.CategoryRepository;
import com.rv02.AssetManagement.dao.EmployeeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The main application class which runs SpringBootApplication
 *
 * <p>The 3 repositories CategoryRepository, AssetRepository, Employee
 * Repository are enabled on startup</p>
 */
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {CategoryRepository.class,
		AssetRepository.class, EmployeeRepository.class})
public class AssetManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssetManagementApplication.class, args);
	}

}

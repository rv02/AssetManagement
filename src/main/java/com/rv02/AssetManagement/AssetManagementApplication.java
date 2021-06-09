package com.rv02.AssetManagement;

import com.rv02.AssetManagement.dao.AssetRepository;
import com.rv02.AssetManagement.dao.CategoryRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {CategoryRepository.class,
		AssetRepository.class})
public class AssetManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssetManagementApplication.class, args);
	}

}

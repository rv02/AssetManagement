package com.rv02.AssetManagement.dao;

import com.rv02.AssetManagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * EmployeeRepository interface for general purpose data access.
 * <p>The class provides the mechanism for storage, retrieval, search, update and delete operation on objects</p>
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}

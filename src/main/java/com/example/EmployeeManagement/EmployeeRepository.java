package com.example.EmployeeManagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employees, Long> {
	
	@Query("SELECT e FROM Employees e WHERE e.email = ?1")
	Employees findByEmail(String Email);
}

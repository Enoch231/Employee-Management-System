package com.example.EmployeeManagement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE )
@Rollback(false )
public class EmployeesRepositoryTests {
	
	@Autowired
	private EmployeeRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateEmployee() {
		Employees employee = new Employees();
		employee.setEmail("maverick@gmail.com");
		employee.setPassword("fantastic");
		employee.setFirstName("Curry");
		employee.setLastName("White");
		
		Employees savedEmployee= repo.save(employee);
		
		Employees existEmployee =entityManager.find(Employees.class, savedEmployee.getId());
		
		assertThat(existEmployee.getEmail()).isEqualTo(employee.getEmail());
	}
	
	@Test	
	public void testFindEmployeeByEmail() {
		String email = "koby@gmail.com";
		
		Employees employee = repo.findByEmail(email);
		
		assertThat(employee).isNotNull();
		
		

	}
	

}

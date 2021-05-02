package com.example.EmployeeManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class EmployeeService {
	@Autowired
	private EmployeeRepository repo;
	
	public void save(Employees employees) {
		repo.save(employees);
	}
	
	public Employees get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete (Long id) {
		repo.deleteById(id);
	}

}

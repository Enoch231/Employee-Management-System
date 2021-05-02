package com.example.EmployeeManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomEmployeeDetailsService implements UserDetailsService {
	
	@Autowired
	private EmployeeRepository repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Employees employee = repo.findByEmail(email);
		if (email == null) {
			throw new UsernameNotFoundException("Employee Not Found");
		}
		
		return new CustomEmployeeDetails(employee);
	}

}

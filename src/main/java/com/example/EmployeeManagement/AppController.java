package com.example.EmployeeManagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	@Autowired
	private EmployeeRepository repo;
	@Autowired
	private EmployeeService service;
	
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showSignUpForm(Model model) {
		model.addAttribute("employee", new Employees());
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegistration(@ModelAttribute Employees employee) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(employee.getPassword());
		employee.setPassword(encodedPassword);
		
		repo.save(employee);
		return "register_success";
	}
	
	
	
	@GetMapping("/list_employees")
	public String viewEmployeeslist(Model model) {
		List<Employees> listEmployees = repo.findAll();
		model.addAttribute("listEmployees", listEmployees);
		return "employees";
	}
	
	@RequestMapping("/new")
	public String showNewEmployeeForm(Model model) {
		Employees employee = new Employees();
		model.addAttribute("employee", employee);
		return "new_employee";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveEmployee(@ModelAttribute("employee") Employees employees) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(employees.getPassword());
		employees.setPassword(encodedPassword);
		repo.save(employees);
		
		return "redirect:/list_employees";  
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name="id") Long id) {
		ModelAndView mav = new ModelAndView("edit_employee");
		
		Employees employee = service.get(id);
		mav.addObject("employee", employee);
		
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name="id") Long id) {
		service.delete(id);
		
		return "redirect:/list_employees";
		
	}

}

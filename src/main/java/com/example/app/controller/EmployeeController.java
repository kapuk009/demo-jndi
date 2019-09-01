package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/addEmployee")
	public String addEmployee(@RequestBody String body) {
		System.out.println("body : " + body);
		
		employeeService.addEmployee(body);
		
		return "Add Complete";
	}
	
	@PostMapping("/getAllEmployee")
	public String getAllEmployee() {
		
		return employeeService.getAllEmployee();
	}
	
	@PostMapping("/getEmployeeById")
	public String getEmployeeById(@RequestBody String body) {
		System.out.println("body : " + body);
		
		return employeeService.getEmployeeById(body);
	}
	
	@PostMapping("/getEmployeeByName")
	public String getEmployeeByName(@RequestBody String body) {
		System.out.println("body : " + body);
		
		return employeeService.getEmployeeByName(body);
	}

}

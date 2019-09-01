package com.example.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.entity.Employee;
import com.example.app.repository.EmployeeRepository;
import com.google.gson.Gson;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	
	public String addEmployee(String jsonData) {
		String result = "";
		
		Gson gson = new Gson();
		Employee employee = gson.fromJson(jsonData, Employee.class);
		
//		how to check save success/failed
		if(employeeRepository.getAddEmployee(employee)>0) {
			result = "saved success";
		} else {
			result = "saved failed";
		}

		return result;
	}
	
	public String getAllEmployee() {
		String result = "";
		
		List<Employee> listEmployee = (List<Employee>) employeeRepository.getAllEmployee();

		Gson gson = new Gson();
		result = gson.toJson(listEmployee);
		
		return result;
		
	}
	
	public String getEmployeeById(String jsonData) {
		String result = "";
		Gson gson = new Gson();
		
		Employee jsonEmployee = gson.fromJson(jsonData, Employee.class);
		Employee employee = employeeRepository.getEmployeeById(jsonEmployee.getEmployeeId());
		
		result = gson.toJson(employee);
		
		return result;
		
	}
	
	public String getEmployeeByName(String jsonData) {
		String result = "";
		Gson gson = new Gson();
		
		Employee jsonEmployee = gson.fromJson(jsonData, Employee.class);
		List<Employee> listEmployee =  (List<Employee>) employeeRepository.getEmployeeByName(jsonEmployee.getEmployeeName());
		
		result = gson.toJson(listEmployee);
		
		return result;
		
	}
	
	
}

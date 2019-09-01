package com.example.app.entity;

public class Employee {

	private int employeeId;
	private String employeeName;
	private String employeeSurName;
	private int departmentId;
	
	Employee(){}
	
	public Employee(int employeeId, String employeeName, String employeeSurName, int departmentId){
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeSurName = employeeSurName;
		this.departmentId = departmentId;
	}

	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public String getEmployeeName() {
		return employeeName;
	}


	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	public String getEmployeeSurName() {
		return employeeSurName;
	}


	public void setEmployeeSurName(String employeeSurName) {
		this.employeeSurName = employeeSurName;
	}


	public int getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	
}

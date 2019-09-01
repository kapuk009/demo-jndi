package com.example.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.app.entity.Employee;

@Repository
public class EmployeeRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public List<Employee> getAllEmployee() {
	   String sql = "SELECT employee_Id, employee_Name, employee_SurName, department_Id FROM kuldb.Employee";
	   RowMapper<Employee> rowMapper = new BeanPropertyRowMapper<Employee>(Employee.class);
	   return this.jdbcTemplate.query(sql, rowMapper);
	} 
	
	public int getAddEmployee(Employee employee) { 
		   String sql = "Insert into kuldb.employee(employee_id, employee_name, employee_surname, department_id) \n" + 
			   		"(select max(employee_id) + 1, :empName, :empSurName, :depId from kuldb.employee)";
		   
		   HashMap m = new HashMap();
		   m.put("empName", employee.getEmployeeName());
		   m.put("empSurName", employee.getEmployeeSurName());
		   m.put("depId", employee.getDepartmentId());
		   
		   int insertRow = namedParameterJdbcTemplate.update(sql, m);
		   
		   return insertRow;
	} 
	
	public Employee getEmployeeById(int empId) {
		   String sql = "SELECT employee_Id, employee_Name, employee_SurName, department_Id FROM kuldb.Employee"
		   		+ " where employee_id = :empId";
		   
		   HashMap m = new HashMap();
		   m.put("empId", empId);
		   
		   Employee employee = (Employee) namedParameterJdbcTemplate.queryForObject(sql, m, (rs, row) -> 
		   		new Employee(
				   rs.getInt("employee_id")
				   , rs.getString("employee_Name")
				   , rs.getString("employee_SurName")
				   , rs.getInt("department_Id")
				   )
		   );
		   
		   return employee;
	} 
	
	public List<Employee> getEmployeeByName(String empName) {
		   String sql = "SELECT employee_Id, employee_Name, employee_SurName, department_Id FROM kuldb.Employee"
				   + " where employee_name like :empName";
				   
		   HashMap m = new HashMap();
		   m.put("empName", "%" + empName + "%");
		   
		   
		   List<Employee> listEmployee = namedParameterJdbcTemplate.query(sql, m, (rs, row) -> 
	   		new Employee(
			   rs.getInt("employee_id")
			   , rs.getString("employee_Name")
			   , rs.getString("employee_SurName")
			   , rs.getInt("department_Id")
			   )
			);
		   
		   return listEmployee;
	} 
}

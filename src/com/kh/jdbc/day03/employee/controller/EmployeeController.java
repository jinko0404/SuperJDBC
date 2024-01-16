package com.kh.jdbc.day03.employee.controller;

import java.util.List;

import com.kh.jdbc.day03.employee.model.dao.EmployeeDAO;
import com.kh.jdbc.day03.employee.model.vo.Employee;

public class EmployeeController {
	private EmployeeDAO eDao;
	
	public Employee printOneEmployee(String empId) {
		Employee emp = eDao.findEmployee(empId);
		return emp;
	}
	
	public void modifyEmployee(Employee emp) {
		eDao.updateEmployee(emp);
	}
	
	public EmployeeController() {
		eDao = new EmployeeDAO();
	}
	
	public void registerEmployee(Employee emp) {
		eDao.insertEmployee(emp);
	}
	
	public List<Employee> printAllEmployees() {
		List<Employee> eList = eDao.selectAllEmployees();
		return eList;
	}
	
	public void removeEmployee(String empId) {
		eDao.deleteEmployee(empId);
	}
}

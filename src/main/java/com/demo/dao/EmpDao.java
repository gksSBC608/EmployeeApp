package com.demo.dao;

import java.util.List;

import com.demo.entity.Employee;
import com.demo.exception.SqlException;

public interface EmpDao {

	public Integer addEmployee(Employee e) throws SqlException;

	public Employee findEmployee(int id) throws SqlException;

	public List<Employee> findByDesignation(String designation)
			throws SqlException;

	public boolean removeEmployee(int id) throws SqlException;

	public Boolean updateEmployee(Employee emp) throws SqlException;

	public List<Employee> getAllEmployees() throws SqlException;

}

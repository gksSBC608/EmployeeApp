package com.demo.service;

import java.util.List;

import com.demo.entity.Employee;
import com.demo.exception.ServiceException;

/**
 * 
 * @author Gaurav
 *
 */
public interface Service {

	/**
	 * 
	 * @param e
	 * @return
	 * @throws ServiceException
	 */
	public boolean addEmployee(Employee e) throws ServiceException;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public Employee findEmployee(int id) throws ServiceException;

	/**
	 * 
	 * @param designation
	 * @return
	 * @throws ServiceException
	 */
	public List<Employee> findByDesignation(String designation)
			throws ServiceException;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public boolean removeEmployee(int id) throws ServiceException;

	/**
	 * 
	 * @param emp
	 * @return
	 * @throws ServiceException
	 */
	public Boolean updateEmployee(Employee emp) throws ServiceException;

	/**
	 * 
	 * @return
	 */
	public List<Employee> getAllEmployees() throws ServiceException;

}
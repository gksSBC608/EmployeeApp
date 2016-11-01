/**
 * 
 */
package com.demo.service;

import java.util.List;

import com.demo.dao.EmpDao;
import com.demo.dao.EmpDaoImpl;
import com.demo.entity.Employee;
import com.demo.exception.ServiceException;
import com.demo.exception.SqlException;

/**
 * @author Gaurav
 *
 */
public class ServiceImpl implements Service {

	EmpDao dao;

	/**
	 * 
	 */
	public ServiceImpl() {
		dao = new EmpDaoImpl();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.demo.service.Service#addEmployee(com.demo.entity.Employee)
	 */
	public boolean addEmployee(Employee emp) throws ServiceException {

		try {
			if (dao.addEmployee(emp) > 0)
				return true;
			else
				return false;
		} catch (SqlException e1) {
			throw new ServiceException(e1);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.demo.service.Service#findEmployee(int)
	 */
	public Employee findEmployee(int id) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return dao.findEmployee(id);
		} catch (SqlException e) {
			throw new ServiceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.demo.service.Service#findByDesignation(java.lang.String)
	 */
	public List<Employee> findByDesignation(String designation)
			throws ServiceException {
		try {
			return dao.findByDesignation(designation);
		} catch (SqlException e) {
			throw new ServiceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.demo.service.Service#removeEmployee(int)
	 */
	public boolean removeEmployee(int id) throws ServiceException {
		try {
			return dao.removeEmployee(id);
		} catch (SqlException e) {
			throw new ServiceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.demo.service.Service#updateEmployee(com.demo.entity.Employee)
	 */
	public Boolean updateEmployee(Employee emp) throws ServiceException {
		try {
			return dao.updateEmployee(emp);
		} catch (SqlException e) {
			throw new ServiceException(e);
		}
	}

	public List<Employee> getAllEmployees() throws ServiceException {

		try {
			return dao.getAllEmployees();
		} catch (SqlException e) {
			throw new ServiceException(e);
		}
	}

}

/**
 * 
 */
package com.demo.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.demo.dao.*;
import com.demo.entity.Employee;
import com.demo.exception.SqlException;

/**
 * @author Gaurav
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDao {

	private static Employee emp;
	private static EmpDao dao;

	private static Integer empId;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		emp = new Employee();
		emp.setName("Kishor Kunal");
		emp.setDesignation("manager");
		emp.setSalary(115000.0f);

		dao = new EmpDaoImpl();
		empId = null;

	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.demo.dao.EmpDaoImpl#addEmployee(com.demo.entity.Employee)}.
	 * 
	 * @throws SqlException
	 */
	@Test
	public void testAddEmployee() throws SqlException {
		empId = dao.addEmployee(emp);
		assertNotNull(empId);
	}

	/**
	 * Test method for {@link com.demo.dao.EmpDaoImpl#findEmployee(int)}.
	 * 
	 * @throws SqlException
	 */
	@Test
	public void testFindEmployee() throws SqlException {
		assertNotNull(dao.findEmployee(1));
	}

	/**
	 * Test method for
	 * {@link com.demo.dao.EmpDaoImpl#findByDesignation(java.lang.String)}.
	 * 
	 * @throws SqlException
	 */
	@Test
	public void testFindByDesignation() throws SqlException {
		java.util.List<Employee> list = dao.findByDesignation("engineer");
		assertTrue(list.size() > 0);
	}

	/**
	 * Test method for {@link com.demo.dao.EmpDaoImpl#removeEmployee(int)}.
	 * 
	 * @throws SqlException
	 */
	@Test
	public void testRemoveEmployee() throws SqlException {
		assertTrue(dao.removeEmployee(empId));
	}

	/**
	 * Test method for
	 * {@link com.demo.dao.EmpDaoImpl#updateEmployee(com.demo.entity.Employee)}.
	 * 
	 * @throws SqlException
	 */
	@Test
	public void testUpdateEmployee() throws SqlException {
		Employee emp = new Employee();
		emp = dao.findEmployee(2);
		emp.setDesignation("off duty");
		emp.setSalary(0f);
		assertTrue(dao.updateEmployee(emp));
	}

}

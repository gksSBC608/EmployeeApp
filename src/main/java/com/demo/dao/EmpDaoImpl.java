package com.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.entity.Employee;
import com.demo.exception.SqlException;

public class EmpDaoImpl implements EmpDao {

	public EmpDaoImpl() {

	}

	public Integer addEmployee(Employee emp) throws SqlException {
		Session session;
		Transaction tx = null;
		try {
			session = HibernateUtil.getHibernateSession();
			tx = session.beginTransaction();
		} catch (Exception e) {
			throw new SqlException(e.getMessage());
		}
		try {

			session.save(emp);

			Integer generatedId = emp.getId();

			tx.commit();
			return generatedId;

		} catch (HibernateException e) {

			e.printStackTrace();

			tx.rollback();
			throw new SqlException("There was an error in adding Employee\n "
					+ emp + " \nto database", e);

		} finally {

			session.clear();
			session.close();

		}

	}

	public Employee findEmployee(int id) throws SqlException {
		Session session = null;
		String fetchEmployeeByID = "from Employee employee where employee.id = "
				+ id;
		try {
			session = HibernateUtil.getHibernateSession();
			Query query = session.createQuery(fetchEmployeeByID);
			return (Employee) query.uniqueResult();

		} catch (HibernateException e) {

			e.printStackTrace();

			throw new SqlException(
					"There was an error while fetching Employee by ID\n " + id,
					e);

		} finally {

			session.clear();
			session.close();

		}

	}

	public List<Employee> findByDesignation(String designation)
			throws SqlException {
		Session session = null;
		String fetchEmployeeByID = "from Employee employee where employee.designation ='"
				+ designation + "'";
		try {
			session = HibernateUtil.getHibernateSession();
			Query query = session.createQuery(fetchEmployeeByID);
			return query.list();

		} catch (HibernateException e) {

			e.printStackTrace();

			throw new SqlException(
					"There was an error while fetching Employee by designation\n "
							+ designation, e);

		} finally {

			session.clear();
			session.close();

		}

	}

	public boolean removeEmployee(int id) throws SqlException {
		Session session = null;
		Transaction tx = null;

		try {
			session = HibernateUtil.getHibernateSession();
			tx = session.beginTransaction();
		} catch (Exception e) {
			throw new SqlException(e.getMessage());
		}

		try {

			Employee emp = (Employee) session.load(Employee.class, id);
			if (emp != null) {
				session.delete(emp);
			}
			tx.commit();
			return true;
		} catch (HibernateException e) {

			e.printStackTrace();

			tx.rollback();
			throw new SqlException(
					"There was an error in removing Employee with id \n " + id
							+ " \nfrom database", e);

		} finally {

			session.clear();
			session.close();

		}
	}

	public Boolean updateEmployee(Employee emp) throws SqlException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getHibernateSession();
			tx = session.beginTransaction();
		} catch (Exception e) {
			throw new SqlException(e.getMessage());
		}

		try {

			session.saveOrUpdate(emp);
			tx.commit();

			return true;

		} catch (HibernateException e) {

			e.printStackTrace();

			tx.rollback();
			throw new SqlException("There was an error in adding Employee\n "
					+ emp + " \nto database", e);

		} finally {

			session.clear();
			session.close();

		}

	}

	public List<Employee> getAllEmployees() throws SqlException {
		Session session = null;

		String fetchEmployeeByID = "from Employee";
		try {
			session = HibernateUtil.getHibernateSession();
			Query query = session.createQuery(fetchEmployeeByID);
			return query.list();

		} catch (HibernateException e) {

			e.printStackTrace();

			throw new SqlException(
					"There was an error while fetching Employees ", e);

		} finally {

			session.clear();
			session.close();

		}
	}

}

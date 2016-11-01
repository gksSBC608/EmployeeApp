package com.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.entity.Employee;
import com.demo.exception.ServiceException;
import com.demo.service.Service;
import com.demo.service.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Service service;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {

		service = new ServiceImpl();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("add")) {
			addEmployee(request, response);
		}
		if (uri.contains("querybyid")) {
			getEmployeeById(request, response);
		}
		if (uri.contains("querybydsgn")) {
			getEmployeeByDesignation(request, response);
		}
		if (uri.contains("remove")) {
			removeEmployee(request, response);
		}
		if (uri.contains("update")) {
			updateEmployee(request, response);
		}

		if (uri.contains("put")) {
			putEmployee(request, response);
		}

		if (uri.contains("ajax")) {
			processAjaxRequest(request, response);
		}
		if (uri.contains("emplist")) {
			getEmployeeList(request, response);
		}

	}

	private void getEmployeeList(HttpServletRequest request, HttpServletResponse response) {
		String designation = request.getParameter("dsgn");
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json");
		List<Employee> list = new ArrayList<Employee>();

	}

	private void processAjaxRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json");
		Employee emp = null;

		try {
			emp = service.findEmployee(id);
		} catch (ServiceException e) {
			request.setAttribute("message", e.getMessage());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
		}

		mapper.writeValue(response.getOutputStream(), emp);

	}

	private void putEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String designation = request.getParameter("designation");
		String salStr = request.getParameter("salary");

		if (!Pattern.matches("^[A-Za-z]{1,}[\\s]{0,}[A-Za-z\\s]{0,}$", designation)) {
			request.setAttribute("message", "Designation of unexpected format found");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
		}

		if (!Pattern.matches("^\\d+(\\.\\d{1,2})?$", salStr)) {
			request.setAttribute("message", "Salary is not in expected decimal format");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
		}

		float salary = Float.valueOf(salStr);

		Employee emp = new Employee(id, name, designation, salary);
		Boolean updated = false;
		RequestDispatcher dispatcher;
		try {
			updated = service.updateEmployee(emp);
			request.setAttribute("message", "Employee " + name + " updated successfully");
			dispatcher = getServletContext().getRequestDispatcher("/success.jsp");
			// dispatcher.forward(request, response);
		} catch (ServiceException e) {
			request.setAttribute("message", e.getMessage());
			dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
			// dispatcher.forward(request, response);
		}
		dispatcher.forward(request, response);

	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Employee> list = new ArrayList<Employee>();
		RequestDispatcher dispatcher;
		try {
			list = service.getAllEmployees();
			request.setAttribute("list", list);
			dispatcher = getServletContext().getRequestDispatcher("/updateForm.jsp");
			// dispatcher.forward(request, response);
		} catch (ServiceException e) {
			request.setAttribute("message", e.getMessage());
			dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
			// dispatcher.forward(request, response);
		}
		dispatcher.forward(request, response);
	}

	private void removeEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Boolean removed = false;
		RequestDispatcher dispatcher;
		try {
			removed = service.removeEmployee(id);
			request.setAttribute("message", "Employee wit id " + id + " removed from record");
			dispatcher = getServletContext().getRequestDispatcher("/success.jsp");

		} catch (ServiceException e) {
			request.setAttribute("message", e.getMessage());
			dispatcher = getServletContext().getRequestDispatcher("/error.jsp");

		}
		dispatcher.forward(request, response);

	}

	private void getEmployeeByDesignation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String designation = request.getParameter("dsgn");
		List<Employee> list = new ArrayList<Employee>();
		RequestDispatcher dispatcher;
		try {
			list = service.findByDesignation(designation);
			request.setAttribute("list", list);
			dispatcher = getServletContext().getRequestDispatcher("/queryApp.jsp");

		} catch (ServiceException e) {
			request.setAttribute("message", e.getMessage());
			dispatcher = getServletContext().getRequestDispatcher("/error.jsp");

		}
		dispatcher.forward(request, response);

	}

	private void getEmployeeById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Employee emp = null;
		RequestDispatcher dispatcher;
		try {
			emp = service.findEmployee(id);
			request.setAttribute("emp", emp);
			dispatcher = getServletContext().getRequestDispatcher("/queryApp.jsp");
		} catch (ServiceException e) {
			request.setAttribute("message", e.getMessage());
			dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
		}

	}

	private void addEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String designation = request.getParameter("designation");
		float salary = Float.valueOf(request.getParameter("salary"));

		Employee emp = new Employee(null, name, designation, salary);
		RequestDispatcher dispatcher;
		try {
			service.addEmployee(emp);
			request.setAttribute("message", "Employee " + name + " added successfully");
			dispatcher = getServletContext().getRequestDispatcher("/success.jsp");

		} catch (ServiceException e) {
			request.setAttribute("message", "Employee " + name + " couldn't be added to database");
			dispatcher = getServletContext().getRequestDispatcher("/error.jsp");

		}
		dispatcher.forward(request, response);
	}

}

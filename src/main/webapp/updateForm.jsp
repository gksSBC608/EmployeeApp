<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
	function validate() {
		var dE = document.getElementById("dsgn");
		var sE = document.getElementById("salary");
		var designation = dE.value;
		var salary = sE.value;
		var validated = true;
		var alphaExp = /^[A-Za-z]{1,}[\s]{0,}[A-Za-z\s]{0,}$/;
		if (!designation.match(alphaExp)) {
			validated = false;
			dE.focus();
			dError.innerHTML = "Designation should be of expected format";
		}
		var salaryExp = /^\d+(\.\d{1,2})?$/;
		if (!salary.match(salaryExp)) {
			validated = false;
			sE.focus();
			sError.innerHTML = "Salary should be a decimal with 2 precision";
		}

		return validated;

	}
</script>

<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<h2 align=center>Update Employee</h2>
	<div class="container-fluid">
		<div class=row>
			<div class="col-sm-offset-10 col-sm-2">
				<a href="index.html">Go To Home</a>
			</div>
		</div>
		<div class="container" name=result>

			<div class=row>
				<div class="col-sm-offset-2 col-sm-8">
					<c:if test="${list!=null }">

						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th>Emp ID</th>
									<th>Name</th>
									<th>Designation</th>
									<th>Salary</th>
									<th></th>
								</tr>
							</thead>
							<c:forEach items="${list}" var="emp">

								<tr>
									<form action="put.do" method="post">
										<td>${emp.id}<input type="hidden" name="id"
											value="${emp.id}">
										</td>
										<td>${emp.name}<input type="hidden" name="name"
											value="${emp.name}">
										</td>
										<td><input type="text" name="designation" id="dsgn"
											value="${emp.designation}"></td>
										<td><input type="text" name="salary" id="salary"
											value="${emp.salary}"></td>
										<td><input type="submit" value="UPDATE"
											onclick="return validate()"></td>

									</form>
								</tr>

							</c:forEach>
						</table>


					</c:if>
				</div>
			</div>
		</div>
	</div>


	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
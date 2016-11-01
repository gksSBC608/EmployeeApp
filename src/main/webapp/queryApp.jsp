<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script src="js/jquery.min.js"></script>
<script>
	/* jQuery methods */

	$(document)
			.ready(
					function() {
						$('#getemp')
								.click(
										function() {
											var id = $('#id1').val();
											var exp = /^\d+$/;
											if (id == "") {
												$('#id1').attr("placeholder",
														"id is mandatory");
												$('#err')
														.html(
																"<em>ID is mandatory</em>");
											} else if (!id.match(exp)) {
												$('#err')
														.html(
																"<em>ID should be numeric</em>");
											} else {
												$
														.ajax({
															url : "ajax.do",
															type : "get", //send it through get method
															data : {
																"id" : id
															},
															success : function(
																	data) {
																//Do Something
																alert("success"
																		+ data);
																//var table ="<table class='table table-striped table-hover'>";
																var tbody = $("<tbody/>");
																var tr = $("<tr/>");
																var td_id = $("<td/>");
																var span1 = $("<span />");
																span1
																		.text(data.id);
																td_id
																		.append(span1);
																var td_name = $("<td/>");
																var span2 = $("<span />");
																span2
																		.text(data.name);
																td_name
																		.append(span2);
																var td_designation = $("<td/>");
																var span3 = $("<span />");
																span3
																		.text(data.designation);
																td_designation
																		.append(span3);

																var td_salary = $("<td/>");
																var span4 = $("<span />");
																span4
																		.text(data.salary);
																td_salary
																		.append(span4);

																var thead = "<thead><tr><th>Emp ID</th>";
																thead += "<th>Name</th><th>Designation</th><th>Salary</th></tr></thead>";
																$('#ajax-table')
																		.html(
																				"");

																tr
																		.append(
																				td_id)
																		.append(
																				td_name)
																$('#ajax-table')
																		.append(
																				thead)
																		.append(
																				tbody)
																		.append(
																				$('<tr/>'))

																$(
																		'#ajax-table tbody tr')
																		.append(
																				td_id)
																		.append(
																				td_name)
																		.append(
																				td_designation)
																		.append(
																				td_salary);

															},
															error : function(
																	xhr) {
																//Do Something to handle error
																alert("error in ajax");
															}
														});
											}
										});
					});
</script>
<style>
.error {
	color: red;
}
</style>
<script>
	function validateId() {
		var idE = document.getElementById("id");
		var id = idE.value;
		var validated = true;
		var exp = /^\d+$/;
		if (!id.match(exp)) {
			validated = false;
			idE.focus();
			validated = false;
		}
		return validated;
	}

	function validateDesignation() {
		var dE = document.getElementById("dsgn");
		var designation = dE.value;
		var validated = true;
		var exp = /^[A-Za-z]{1,}[\s]{0,}[A-Za-z\s]{0,}$/;
		if (!designation.match(exp)) {
			validated = false;
			dE.focus();
			validated = false;
		}
		return validated;
	}
</script>

<link href="css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
	<c:set var="option" value="${param.a}" />

	<c:if test="${option==1}">
		<h2 align=center>Find Employee</h2>
	</c:if>
	<c:if test="${option==2}">
		<h2 align=center>Find Employees</h2>
	</c:if>
	<c:if test="${option==3}">
		<h2 align=center>Remove Employee</h2>
	</c:if>
	<c:if test="${emp!=null || list !=null }">
		<h2 align=center>Result</h2>
	</c:if>

	<div class="container-fluid">
		<div class=row>
			<div class="col-sm-offset-10 col-sm-2">
				<a href="index.html">Go To Home</a>
			</div>
		</div>
		<div class=row tag=query>
			<c:if test="${option==1}">
				<div class="row">
					<div class="col-sm-offset-4 col-sm-4">
						<form class="form-inline">
							<!-- action="querybyid.do" -->
							<div class="form-group">
								<label for="id">Employee ID :</label> <input type="text"
									placeholder="Input an existing employee ID" name="id" id="id1">
							</div>

							<input type="button" class="btn btn-default" id="getemp"
								value="Get
						Employee" />
							<!-- onclick="return validateId()" -->
						</form>
					</div>
					<div class="col-sm-3">
						<span id="err" class="error"></span>
					</div>
				</div>
			</c:if>
			<c:if test="${option==2}">

				<form class="form-inline" action="querybydsgn.do">
					<div class="form-group">
						<label for="id">Designation :</label> <input type="text"
							placeholder="Input a designation" name="dsgn" id="dsgn">
					</div>

					<button type="submit" class="btn btn-default"
						onclick="return validateDesignation()">Get Employee</button>
				</form>

			</c:if>
			<c:if test="${option==3}">

				<form class="form-inline" action="remove.do">
					<div class="form-group">
						<label for="id">Employee ID :</label> <input type="text"
							placeholder="Input an existing employee ID" name="id" id="id">
					</div>

					<button type="submit" class="btn btn-default"
						onclick="return validateId()">Remove Employee</button>
				</form>
			</c:if>
		</div>

	</div>

	<div class="container" id=result>
		<table class="table table-striped table-hover" id="ajax-table"></table>

		<div class=row>
			<div class="col-sm-offset-4 col-sm-4">${message}</div>
		</div>



		<div class=row>
			<div id=ajax_result></div>
			<div class="col-sm-offset-2 col-sm-8">
				<c:if test="${emp!=null }">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>Emp ID</th>
								<th>Name</th>
								<th>Designation</th>
								<th>Salary</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${emp.id}</td>
								<td>${emp.name}</td>
								<td>${emp.designation}</td>
								<td>${emp.salary}</td>
							</tr>
						</tbody>
					</table>
				</c:if>

				<c:if test="${list!=null }">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>Emp ID</th>
								<th>Name</th>
								<th>Designation</th>
								<th>Salary</th>
							</tr>
						</thead>
						<c:forEach items="${list}" var="emp">
							<tr>
								<td>${emp.id}</td>
								<td>${emp.name}</td>
								<td>${emp.designation}</td>
								<td>${emp.salary}</td>
							</tr>
						</c:forEach>
					</table>


				</c:if>
			</div>
		</div>


		<footer></footer>

		<!-- jQuery -->
		<script src="js/jquery.min.js"></script>

		<!-- Bootstrap Core JavaScript -->
		<script src="js/bootstrap.min.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Add Employee</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/validate.js"></script>
<style>
.error{
color:red
}
</style>
</head>
<body>
	<h2 align=center>Add Employee</h2>

	<header></header>

	<div class="container-fluid">
		<div class=row>
			<div class="col-sm-offset-10 col-sm-2">
				<a href="index.html">Go To Home</a>
			</div>
		</div>
		<form class="form-horizontal" action="add.do" onmouseup="initialize()">
			<div class="form-group">
				<label class="control-label col-sm-3" for="name">Name:</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="name" id="name">
				</div>
				<div id="nameError" class="col-sm-4 error"></div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="dsgn">Designation:</label>
				<div class="col-sm-5">
					<input type="text" name="designation" id="dsgn"
						class="form-control">
				</div>
				<div id="dsgnError" class="col-sm-4 error"></div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-3" for="salary">Salary:</label>
				<div  class="col-sm-5">
					<input class="form-control" type="text" name="salary" id="salary">
				</div>
				<div class="col-sm-4 error" id="salaryError"></div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-10">
					<button type="submit" class="btn btn-default"
						onclick="return validate()">Submit</button>
				</div>
			</div>
		</form>
	</div>

	<footer></footer>

	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

</body>
</html>
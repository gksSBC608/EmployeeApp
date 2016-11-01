<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<h2 align=center>Error Page</h2>
	<div class="container-fluid">
		<div class=row>
			<div class="col-sm-offset-10 col-sm-2">
				<a href="index.html">Go To Home</a>
			</div>
		</div>

		<div class=row>
			<div class="col-sm-offset-3 col-sm-5" style="color: red">${message}</div>
		</div>

	</div>

	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width">
<title>Chipone/iML Project Database</title>
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/welcome.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/list.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<style>
#project, #datasheet, #admin {
	width: 150px;
	height: 150px;
	font-size: 20px;
	padding-top: 12%
}

#add-admin {
	float: right;
}

#datashhet {
	color: #4CAF50;
	background-color: #4CAF50
}

.btn {
	display: inline-block;
	padding: 15px 25px;
	font-size: 24px;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	outline: none;
	border: none;
	border-radius: 15px;
	box-shadow: 0 9px #999;
}

.btn:active {
	box-shadow: 0 5px #666;
	transform: translateY(4px);
}
.btn:disabled {
	box-shadow: none;
	transform: none;
}
#logout-btn {
	float: right;
}
</style>
<body>
	<div class="dropdown">
		<a class="btn-top" style="margin-right: 15px;" id="logout-btn"
			href="${pageContext.request.contextPath}/logout"
			class="btn btn-primary btn-success pull-right"> <span
			class="glyphicon glyphicon-off"> </i>
		</span> &nbsp Logout
		</a>
	</div>
	<div class="container">
		<div class="row">
			<img
				src="${pageContext.request.contextPath}/resources/img/chipone_logo.png"
				alt="chipone" class="logo-left"> <img
				src="${pageContext.request.contextPath}/resources/img/iml_logo.png"
				alt="iml" class="logo-right">
			<div class="empty"></div>
			<div class="col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3"
				id="main-block">
				<div class="row">
					<div class="button-group" align="center">
						<a type="button" class="btn btn-danger" id="admin"
							href="#" disabled>Add
							Admin</a> &nbsp
						<a type="button" class="btn btn-success"
							id="datasheet" href="${pageContext.request.contextPath}/DSHome">
							Data Sheets </a> &nbsp 
						<a type="button" class="btn btn-primary"
							id="project"
							href="${pageContext.request.contextPath}/projectHome">
							Projects </a>

					</div>
				</div>
			</div>
		</div>
	</div>
	<sec:authorize access="hasRole('ADMIN')">
		<script>
		$('#admin').removeAttr('disabled');
		$('#admin').attr('href', '${pageContext.request.contextPath}/register/admin');
		</script>
	</sec:authorize>
</body>
</html>
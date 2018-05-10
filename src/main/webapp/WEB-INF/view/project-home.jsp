<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Chipone/iML Project Database</title>
<meta name="viewport" content="width=device-width">
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
	#list-btn, #import-btn {
		width: 150px;
	}
#logout-btn {
	float:right;
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
				alt="chipone" class="logo-left"> 
			<img
				src="${pageContext.request.contextPath}/resources/img/iml_logo.png"
				alt="iml" class="logo-right">
			<div class="empty"></div>
			<div class="col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3" id = "main-block">
				<div class="row">
					<h2 id="title" align="center">Chipone iML Project Database</h2>
					<form:form action="${pageContext.request.contextPath}/searchProject" method="GET">
						<div id="custom-search-input" align="center">
							<div class="input-group col-md-12">
								<input type="text" class="  search-query form-control" name="keyword"
									placeholder="Search for Project Name" /> <span
									class="input-group-btn">
									<button class="btn btn-danger" type="submit">
										<span class=" glyphicon glyphicon-search"></span>
									</button>
								</span>
							</div>
						</div>
					</form:form>
					<br> <br>
					<div class="button-group" align="center">
						<sec:authorize access="hasRole('ADMIN')">
						<a type="button" class="btn btn-danger" id="import-btn"
							href="${pageContext.request.contextPath}/add"><i
							class="fa fa-plus" aria-hidden="true"></i>Add Project</a>
						</sec:authorize> 
						<a
							type="button" class="btn btn-danger" id="list-btn"
							href="${pageContext.request.contextPath}/main"> Project List
						</a>
						<br><br><br>
						<a href="${pageContext.request.contextPath}/home">Go
							to home page</a>						
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
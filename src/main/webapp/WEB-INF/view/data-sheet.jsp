<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Table</title>
<meta name="viewport" content="width=device-width">
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/list.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/script.js"></script>

</head>
<style>
#logout-btn {
	float: right;
}

#import, .button-group {
	float: left;
}

body {
	padding: 10px;
}

.navbar {
	background-color: #448aff;
}

.nav a {
	color: #ffffff;
}

.nav a:hover {
	color: #448aff;
}

.btn-yellow {
	background-color: #dddd00;
}

.btn-yellow:hover {
	background-color: #ffff22;
}

.btn-purple {
	background-color: #800080;
}

.btn-purple:hover {
	background-color: #a222a2;
}

.button-group {
	padding-left: 20%;
	text-align;
	center;
}

.button-group a {
	width: 100px;
	padding-top: 20px;
	height: 50px;
	display: inline-block;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	outline: none;
	border: none;
	border-radius: 5px;
	box-shadow: 0 9px #999;
}

.button-group .btn:active {
	box-shadow: 0 5px #666;
	transform: translateY(4px);
}
</style>
<body>
	<h2 style="color: #448aff; text-align: center;">Data Sheet File
		Table</h2>
	<hr>
	<div id="email-info" class="alert alert-info" style="display: none">Sending
		Email</div>
	<c:if test="${param.info=='success'}">
		<div id="success" class="alert alert-success">Email Send
			Successfully!</div>
	</c:if>
	<div class="dropdown">
		<sec:authorize access="hasRole('ADMIN')">
			<a class="btn-top" style="margin-right: 15px;" id="import"
				href="${pageContext.request.contextPath}/importDS"
				class="btn btn-primary btn-success pull-right"> <span
				class="glyphicon glyphicon-plus"></span> &nbsp Import
			</a>
		</sec:authorize>
		<div class="button-group" align="center">
			<a type="button"
				href="${pageContext.request.contextPath}/datasheet/all"
				class="btn btn-danger">ALL</a> <a type="button"
				href="${pageContext.request.contextPath}/datasheet/OPAMP"
				class="btn btn-success">OPAMP</a> <a type="button"
				href="${pageContext.request.contextPath}/datasheet/PGAMMA"
				class="btn btn-primary">PGAMMA</a> <a type="button"
				href="${pageContext.request.contextPath}/datasheet/PMIC"
				class="btn btn-danger">PMIC</a> <a type="button"
				href="${pageContext.request.contextPath}/datasheet/LS"
				class="btn btn-success">LS</a> <a type="button"
				href="${pageContext.request.contextPath}/datasheet/DVR"
				class="btn btn-primary">DVR</a> <a type="button"
				href="${pageContext.request.contextPath}/datasheet/OTHERS"
				class="btn btn-danger">OTHERS</a>
		</div>
		<a class="btn-top" style="margin-right: 15px;" id="logout-btn"
			href="${pageContext.request.contextPath}/logout"
			class="btn btn-primary btn-success pull-right"> <span
			class="glyphicon glyphicon-off"> </i>
		</span> &nbsp Logout
		</a>
	</div>
	<br />
	<br />
	<!-- 
	<nav class="navbar">
	<div class="container-fluid">
		<ul class="nav navbar-nav">
			<li class="active"><a
				href="${pageContext.request.contextPath}/datasheet/all">ALL</a></li>
			<li><a href="${pageContext.request.contextPath}/datasheet/OPAMP">OPAMP</a></li>
			<li><a
				href="${pageContext.request.contextPath}/datasheet/PGAMMA">PGAMMA</a></li>
			<li><a href="${pageContext.request.contextPath}/datasheet/PMIC">PMIC</a></li>
			<li><a href="${pageContext.request.contextPath}/datasheet/LS">LS</a></li>
			<li><a href="${pageContext.request.contextPath}/datasheet/DVR">DVR</a></li>
			<li><a
				href="${pageContext.request.contextPath}/datasheet/OTHERS">OTHERS</a></li>
		</ul>
		<form:form class="navbar-form navbar-left"
			action="${pageContext.request.contextPath}/searchDS" method="GET">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search"
					name="key">
			</div>
			<button type="submit" class="btn btn-default">Search</button>
		</form:form>
	</div>
	</nav>
	 -->
	<br />
	<br />
	<form:form action="${pageContext.request.contextPath}/searchDS"
		method="GET">
		<div class="col-xs-3 input-group" id="search-bar">
			<input type="text" name="key" placeholder="Search For Data Sheet"
				class="search form-control" /> <span class="input-group-btn"><input
				type="submit" value="search" class="btn btn-primary" id="search" /><span>
		</div>
	</form:form>

	<table class="table table-striped">

		<thead>
			<tr class="row-name">
				<th>Product Family</th>
				<th>Device Name</th>
				<th>File Name</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="dataSheet" items="${dataSheets}">
				<c:url var="emailLink" value="/emailDS">
					<c:param name="fileId" value="${dataSheet.id}" />
				</c:url>
				<c:url var="downloadLink" value="/downloadDS">
					<c:param name="fileId" value="${dataSheet.id}" />
				</c:url>
				<c:url var="deleteLink" value="/deleteDS">
					<c:param name="fileId" value="${dataSheet.id}" />
				</c:url>

				<c:url var="previewLink" value="/previewDS">
					<c:param name="fileId" value="${dataSheet.id}" />
				</c:url>
				<script>
					function email() {
						$('#success').hide();
						$('#email-info').show();
					}
				</script>
				<tr class="row-content">
					<td>${dataSheet.productFamily}</td>
					<td>${dataSheet.deviceName}</td>
					<td><a href="${previewLink}">${dataSheet.fileName}</a></td>
					<td><a class="btn btn-info edit" href="${downloadLink}"
						aria-label="Settings"> <i class="fa fa-download"
							aria-hidden="true"></i>Download
					</a> &nbsp <a id="email" class="btn btn-info edit" onclick="email()" href="${emailLink}" 
						aria-label="Settings"> <i class="fa fa-envelope"
							aria-hidden="true"></i>Email
					</a>&nbsp <sec:authorize access="hasRole('ADMIN')">
							<a class="btn btn-danger edit" href="${deleteLink}"
								aria-label="Settings" onclick="confirmDelete()"> <i
								class="fa fa-trash" aria-hidden="true"></i>Delete
							</a>
						</sec:authorize></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<p>
		<a href="${pageContext.request.contextPath}/DSHome">Back to Home</a>
	</p>
</body>
</html>
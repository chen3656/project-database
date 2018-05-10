<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Project List</title>
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
</head>
<style>
#logout-btn {
	float: right;
}
body {
	padding: 10px;
}
</style>
<body>
	<h2 style="color: #448aff; text-align: center;">Project Database</h2>
	<hr>
	<table class="table table-striped">
		<div class="dropdown">
			<sec:authorize access="hasRole('ADMIN')">
				<a class="btn-top" style="margin-right: 15px;"
					href="${pageContext.request.contextPath}/add"
					class="btn btn-primary btn-success pull-right"> <span
					class="glyphicon glyphicon-plus"></i> </span> &nbsp Add Project
				</a>
			</sec:authorize>
			<a class="btn-top" style="margin-right: 15px;" id="logout-btn"
				href="${pageContext.request.contextPath}/logout"
				class="btn btn-primary btn-success pull-right"> <span
				class="glyphicon glyphicon-off"> </i>
			</span> &nbsp Logout
			</a>
		</div>
		<br>
		<form:form action="${pageContext.request.contextPath}/searchProject"
			method="GET">
			<div class="col-xs-3 input-group" id="search-bar">
				<input type="text" name="keyword" placeholder="Search For Project"
					class="search form-control" /> <span class="input-group-btn"><input
					type="submit" value="search" class="btn btn-primary" id="search" /><span>
			</div>
		</form:form>
		<br>
		<br>
		<thead>
			<tr class="row-name">
				<th>Project Name</th>
				<th>Device Name</th>
				<th>Contact Name</th>
				<th>Contact Email</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="project" items="${projects}">
				<c:url var="deleteLink" value="/deleteProject">
					<c:param name="projectId" value="${project.id}" />
				</c:url>
				<c:url var="editLink" value="/edit">
					<c:param name="projectId" value="${project.id}" />
				</c:url>
				<c:url var="versionLink" value="/version">
					<c:param name="projectId" value="${project.id}" />
				</c:url>
				<tr class="row-content">
					<td>${project.name}</td>
					<td>${project.deviceName}</td>
					<td>${project.contactName}</td>
					<td><a href="mailto:${project.contactEmail}">${project.contactEmail}</a></td>
					<td><a class="btn btn-info edit" href="${versionLink}"
						aria-label="Settings"> <i class="fa fa-file"
							aria-hidden="true"></i>Versions
					</a> &nbsp <sec:authorize access="hasRole('ADMIN')">
							<a class="btn btn-danger edit" href="${editLink}"
								aria-label="Settings"> <i class="fa fa-edit"
								aria-hidden="true"></i>Edit
							</a>
					&nbsp
					<a class="btn btn-danger edit" href="${deleteLink}"
								aria-label="Settings" onclick="confirmDelete()"> <i
								class="fa fa-trash" aria-hidden="true"></i>Delete
							</a>
						</sec:authorize></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="${pageContext.request.contextPath}/projectHome">Go to home page</a>
	<script type='text/javascript'
		src="${pageContext.request.contextPath}/resources/js/script.js"></script>
</body>
</html>
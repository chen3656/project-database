<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
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
<script
	src="${pageContext.request.contextPath}/resources/js/script.js"></script>
</head>
<style>
#logout-btn {
	float: right;
}
</style>
<body>
	<h2 style="color: #448aff; text-align: center;">${project.name}</h2>
	<hr>
	<table class="table table-striped">
		<div class="dropdown">
			<a class="btn-top" style="margin-right: 15px;" id="logout-btn"
				href="${pageContext.request.contextPath}/logout"
				class="btn btn-primary btn-success pull-right"> <span
				class="glyphicon glyphicon-off"> </i>
			</span> &nbsp Logout
			</a>
			
		</div>
		</div>
		<thead>
			<tr class="row-name">
				<th>Version Name</th>
				<th>Files</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="version" items="${versions}">
				<c:url var="deleteLink" value="/deleteVersion">
					<c:param name="projectId" value="${project.id}" />
					<c:param name="verId" value="${version.id}" />
				</c:url>
				<c:url var="fileLink" value="/file">
					<c:param name="verId" value="${version.id}" />
				</c:url>
				<tr class="row-content">
					<td>${version.name}</td>
					<td>
						<a class="btn btn-info edit" href="${fileLink}"
							aria-label="Settings"> <i
								class="fa fa-file" aria-hidden="true"></i>File List
						</a>
						&nbsp
						<a class="btn btn-info edit" 
						href="${pageContext.request.contextPath}/download/all?verId=${version.id}"
							aria-label="Settings"> <i
								class="fa fa-file" aria-hidden="true"></i>Donwload All
						</a>
						
					</td>
					<td>
						<sec:authorize access="hasRole('ADMIN')">
						<a class="btn btn-danger edit" href="${deleteLink}"
							aria-label="Settings" onclick="confirmDelete()"> <i
								class="fa fa-trash" aria-hidden="true"></i>Delete
						</a>
						</sec:authorize>
					</td> 
				</tr>
			</c:forEach>
		</tbody>
		
	</table>
	<sec:authorize access="hasRole('ADMIN')">
	<form:form action="${pageContext.request.contextPath}/addVersion?${_csrf.parameterName}=${_csrf.token}" 
		method="POST" modelAttribute="version">
			<input type="hidden" name="projectId" value="${project.id}" />
			<table>
				<tr>
					<td>Add New Version: </td>
					<td><form:input type="text" path="name" placeholder="Version Name"/></td>
					<td><input type="submit" value="Add" class="btn btn-info edit" ></td>
				<tr>
			</table>
	</form:form>
	</sec:authorize>
	<p>
		<a href="${pageContext.request.contextPath}/main">Back to Project List</a>
	</p>
	<script type='text/javascript'
		src="${pageContext.request.contextPath}/resources/js/script.js"></script>
</body>
</html>
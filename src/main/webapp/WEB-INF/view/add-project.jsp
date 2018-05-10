<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<title>Upload Project Device List</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-device-style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Project Information</h2>
		</div>
	</div>
	<div id="container">
		<div id="container">
			<form:form action="${pageContext.request.contextPath}/addProject?${_csrf.parameterName}=${_csrf.token}" method="POST"
				modelAttribute="project">
				<form:input type="hidden" path="id" />
				<table>
					<tbody>
						<tr>
							<td>Project Name:</td>
							<td><form:input type="text" path="name" /></td>
						</tr>
						<tr>
							<td>Device Name:</td>
							<td><form:input type="text" path="deviceName" /></td>
						</tr>
						<tr>
							<td>Contact Name:</td>
							<td><form:input type="text" path="contactName" /></td>
						</tr>
						<tr>
							<td>Contact Email:</td>
							<td><form:input type="text" path="contactEmail" /></td>
						</tr>
						<tr>
							<td><label></label></td>
							<td><input type="submit" value="Save" class="save" /></td>
						</tr>
					</tbody>
				</table>
			</form:form>
		</div>
		<p>
			<a href="${pageContext.request.contextPath}/main">Back to List</a>
		</p>
	</div>
</body>
</html>
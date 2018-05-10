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
			<h2>Upload Data Sheet</h2>
		</div>
	</div>
	<div id="container">
		<p>The file size should be less than 25MB</p>
		<div id="container">
			<form:form action="${pageContext.request.contextPath}/uploadDS?${_csrf.parameterName}=${_csrf.token}" method="POST"
				modelAttribute="dataSheet" enctype="multipart/form-data">
				<table>
					<tbody>
						<tr>
							<td>ProductFamily: </td>
							<td><form:select path="productFamily">
									<form:option value="OPAMP" label="OPAMP" />
									<form:option value="PGAMMA" label="PGAMMA" />		
									<form:option value="PMIC" label="PMIC" />		
									<form:option value="LS" label="LS" />
									<form:option value="DVR" label="DVR" />
									<form:option value="OTHERS" label="OTHERS" />													
								</form:select>
							</td>		
						</tr>
						<tr>
							<td>Device Name: </td>
							<td><form:input type="text" path="deviceName"/></td>
								
						</tr>
						<tr>
							<td>Upload File: </td>
							<td><form:input type="file" path="file" /></td>
						</tr>
						<tr>
							<td><label></label></td>
							<td><input type="submit" value="Upload" class="save" /></td>
						</tr>
					</tbody>
				</table>
			</form:form>
		</div>
		<p>
			<a href="${pageContext.request.contextPath}/datasheet/all">Back to Data Sheet List</a>
		</p>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Register Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<style>
html {
	padding-left: 10px;
}
.error {
	color: red;
}
</style>
<body>
	
	<form:form class="form-horizontal" modelAttribute="userDto"
		action="${pageContext.request.contextPath}/register${admin}?${_csrf.parameterName}=${_csrf.token}"
		method="POST">
		<fieldset>
			<div id="legend">
				<legend class="">Register</legend>
			</div>
			<c:if test="${param.success != null}">
				<div class="alert alert-info">You've successfully registered!
				</div>
			</c:if>
			<c:if test="${param.error != null}">
				<div class="alert alert-danger">There is something wrong.
					Please register again!</div>
			</c:if>
			<div class="control-group">
				<!-- Username -->
				<label class="control-label" for="username">Username</label>
				<div class="controls">
					<input type="text" id="username" name="username" placeholder=""
						class="input-xlarge" required>
					<p class="help-block">Username can contain any letters or
						numbers, without spaces</p>
				</div>
			</div>
			
			<div class="control-group">
				<!-- Username -->
				<label class="control-label" for="username">Email</label>
				<div class="controls">
					<input type="text" id="email" name="email" placeholder=""
						class="input-xlarge" required>
					<p class="help-block">Pleas enter a valid email address</p>
				</div>
			</div>

			<div class="control-group">
				<!-- Password-->
				<label class="control-label" for="password">Password</label>
				<div class="controls">
					<input type="password" id="password" name="password" placeholder=""
						class="input-xlarge" required>
					<form:errors path="password" cssClass="error" />
					<p class="help-block">Password should be at least 4 characters</p>
				</div>
			</div>

			<div class="control-group">
				<!-- Password -->
				<label class="control-label" for="password_confirm">Password
					(Confirm)</label>
				<div class="controls">
					<input type="password" id="password_confirm" name="confirmPassword"
						placeholder="" class="input-xlarge">
					<form:errors path="confirmPassword" cssClass="error" />
					<p class="help-block">Please confirm password</p>
				</div>
			</div>

			<div class="control-group">
				<!-- Button -->
				<div class="controls">
					<button class="btn btn-success">Register</button>
				</div>
			</div>
		</fieldset>
	</form:form>
	<div class="login-register">
		<a href="${pageContext.request.contextPath}/home">Go to Home Page</a>
	</div>
</body>
</html>
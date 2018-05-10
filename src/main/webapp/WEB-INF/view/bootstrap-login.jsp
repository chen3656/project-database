
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/login.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Sign in</title>
<sec:authorize access="isAuthenticated()">
	<%
		response.sendRedirect("/main");
	%>
</sec:authorize>
</head>
<body>
	<!--
    you can substitue the span of reauth email for a input with the email and
    include the remember me checkbox
    -->
	<div class="container">
		<div class="card card-container">
			<!-- <img class="profile-img-card" src="//lh3.googleusercontent.com/-6V8xOA6M7BA/AAAAAAAAAAI/AAAAAAAAAAA/rzlHcD0KYwo/photo.jpg?sz=120" alt="" /> -->
			<img id="profile-img" 
				src="${pageContext.request.contextPath}/resources/img/logo.png" />
			<p id="profile-name" class="profile-name-card"></p>
			<form:form action="${pageContext.request.contextPath}/login"
				modelAttribute="user" class="form-signin" method="POST">
				<span id="reauth-email" class="reauth-email"></span>
				<input type="text" name="username" value="${user.username}"
					id="inputUsername" class="form-control" placeholder="Username"
					required autofocus />
				<input type="password" name="password" value="${user.password}"
					id="inputPassword" class="form-control" placeholder="Password"
					required />
				<button class="btn btn-lg btn-primary btn-block btn-signin"
					type="submit">Sign in</button>
			</form:form>
			<!-- /form -->
			<div class="regiter">
				<span>New user? <a
					href="${pageContext.request.contextPath}/register">Register
						here</a></span>
			</div>
		</div>
		<!-- /card-container -->
	</div>
	<!-- /container -->
</body>
</html>
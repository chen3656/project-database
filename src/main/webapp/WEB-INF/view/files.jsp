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
#email-btn, #all-btn, #logout-btn {
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
	<h2 style="color: #448aff; text-align: center;">${version.name}
		File Table</h2>
	<hr>
	<div id="email-info" class="alert alert-info" style="display: none">Sending
		Email</div>
	<c:if test="${param.info=='success'}">
		<div id="success" class="alert alert-success">Email Send
			Successfully!</div>
	</c:if>
	<div class="dropdown">
		<sec:authorize access="hasRole('ADMIN')">
			<a class="btn-top" style="margin-right: 15px;"
				href="${pageContext.request.contextPath}/import?verId=${version.id}"
				class="btn btn-primary btn-success pull-right"> <span
				class="glyphicon glyphicon-plus"> </span> &nbsp Import
			</a>
		</sec:authorize>
		<a class="btn-top" style="margin-right: 15px;" id="email-btn"
			href="${pageContext.request.contextPath}/email/all?verId=${version.id}"
			class="btn btn-primary btn-success pull-right"> <span
			class="glyphicon glyphicon-envelope"></span> &nbsp Email All
		</a> <a class="btn-top" style="margin-right: 15px;" id="all-btn"
			href="${pageContext.request.contextPath}/download/all?verId=${version.id}"
			class="btn btn-primary btn-success pull-right"> <span
			class="glyphicon glyphicon-download"> </span> &nbsp Download All
		</a>
	</div>
	<table class="table table-striped">

		<thead>
			<tr class="row-name">
				<th>File Description</th>
				<th>File Name</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="file" items="${files}">

				<c:url var="downloadLink" value="/download">
					<c:param name="fileId" value="${file.id}" />
				</c:url>
				<c:url var="emailLink" value="/email">
					<c:param name="fileId" value="${file.id}" />
				</c:url>
				<c:url var="deleteLink" value="/deleteFile">
					<c:param name="verId" value="${version.id}" />
					<c:param name="fileId" value="${file.id}" />
				</c:url>

				<c:url var="previewLink" value="/preview">
					<c:param name="fileId" value="${file.id}" />
				</c:url>
				<script>
					function email() {
						$('#success').hide();
						$('#email-info').show();
					}
				</script>
				<tr class="row-content">
					<td>${file.fileDescription}</td>
					<td><a href="${previewLink}">${file.fileName}</a></td>
					<td><a class="btn btn-info edit" href="${downloadLink}"
						aria-label="Settings"> <i class="fa fa-download"
							aria-hidden="true"></i>Download
					</a> &nbsp <a class="btn btn-info edit" onclick="email()" href="${emailLink}"
						aria-label="Settings"> <i class="fa fa-envelope"
							aria-hidden="true"></i>Email
					</a> &nbsp <sec:authorize access="hasRole('ADMIN')">
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
		<a
			href="${pageContext.request.contextPath}/version?projectId=${projectId}">Back
			to List</a>
	</p>
</body>
</html>
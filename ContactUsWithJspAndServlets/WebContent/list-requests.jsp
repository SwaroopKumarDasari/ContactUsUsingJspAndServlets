<%@ page import="java.util.*,io.mountblue.learn.pojo.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Administrator Panel-Requests</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!-- Compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js">
	
</script>

<style>
div {
	margin-bottom: 20px;
	padding-bottom: 10px;
	padding-top: 10px;
	margin-top: 20px;
}

body {
	background-color: #F3EBF6;
	font-family: 'Ubuntu', sans-serif;
}
</style>
</head>
<body>
	<%
		List<Request> listOfRequests = (ArrayList<Request>) request.getAttribute("requestDetails");
	%>

	<div class="container">
		<div>
			<c:choose>
				<c:when test="${toggler}">

					<div class="header center-align #ba68c8 purple lighten-3">
						<h2>
							<span class="lime-text text-lighten-5">Admin Requests
								Panel</span>
						</h2>
					</div>
				</c:when>
				<c:otherwise>
					<div class="header center-align #ba68c8 purple lighten-3">
						<h2>
							<span class="lime-text text-lighten-5">Admin Archived
								Requests Panel</span>
						</h2>
					</div>
				</c:otherwise>
			</c:choose>
		</div>

		<div>
			<c:choose>
				<c:when test="${toggler}">
					<c:url var="viewArchiveLink" value="/admin/contactUs/requests">
						<c:param name="command" value="VIEW_ARCHIVED" />
					</c:url>
					<a href="${viewArchiveLink}"
						class="waves-effect waves-light btn-large #e57373 red lighten-2">View
						Archived Requests</a>
				</c:when>
				<c:otherwise>
					<c:url var="viewActiveLink" value="/admin/contactUs/requests">
						<c:param name="command" value="SHOW_ACTIVE_REQ" />
					</c:url>
					<a href="${viewActiveLink}"
						class="waves-effect waves-light btn-large #e57373 red lighten-2">View
						Active Requests</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<div class="container">
		<table class="centered">
			<tr>
				<td>Name</td>
				<td>Email</td>
				<td>Message</td>
				<td>Action</td>
			</tr>
			<c:forEach var="tempRequest" items="${requestDetails}">
				<!-- set up a link for each Request -->
				<c:choose>
					<c:when test="${toggler}">
						<c:url var="archiveLink" value="/admin/contactUs/requests">
							<c:param name="command" value="ARCHIVE" />
							<c:param name="requestId" value="${tempRequest.id}" />
						</c:url>
					</c:when>
					<c:otherwise>
						<c:url var="unArchiveLink" value="/admin/contactUs/requests">
							<c:param name="command" value="UN_ARCHIVE" />
							<c:param name="requestId" value="${tempRequest.id}" />
						</c:url>
					</c:otherwise>
				</c:choose>

				<tr>
					<td>${tempRequest.name}</td>
					<td>${tempRequest.email}</td>
					<td>${tempRequest.message}</td>
					<td><c:choose>
							<c:when test="${toggler}">
								<a href="${archiveLink}"
									class="waves-effect waves-light btn-small #e57373 red lighten-2"
									onclick="if (!(confirm('Are you sure you want to archive this request?'))) return false">Archive</a>
							</c:when>
							<c:otherwise>
								<a href="${unArchiveLink}"
									class="waves-effect waves-light btn-small #e57373 red lighten-2"
									onclick="if (!(confirm('Are you sure you want to un-archive this request?'))) return false">UnArchive</a>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>
		</table>
		

		<div>
			<form action="/admin/contactUs/requests">
				<button type="submit"  class="waves-effect waves-light btn-small #e57373 red lighten-2" name="command" value="LOGOUT">Logout</button>
			</form>
		</div>
	</div>
</body>
</html>
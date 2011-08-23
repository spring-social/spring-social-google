<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Home</title>
<link type="text/css" rel="stylesheet" href="resources/style.css" />
</head>
<body>
	<ul>
		<li><a href="<c:url value="/signout" />">Sign Out</a>
		</li>
	</ul>
	<div id="profile">
		<div id="details">
			<h3>Google Profile</h3>
			<div>
				<label>Profile ID:</label> <span>${profile.id}</span>
			</div>
			<div>
				<label>E-mail:</label> <span>${profile.email}</span>
			</div>
			<div>
				<label>Display Name:</label> <span>${profile.name}</span>
			</div>
			<div>
				<label>First Name:</label> <span>${profile.firstName}</span>
			</div>
			<div>
				<label>Last Name:</label> <span>${profile.lastName}</span>
			</div>
			<div>
				<label>Profile URL:</label> <a href="${profile.link}">${profile.link}</a>
			</div>
			<div>
				<label>Gender:</label> <span>${profile.gender}</span>
			</div>
			<div>
				<label>Locale:</label> <span>${profile.locale}</span>
			</div>
		</div>
		<img src="${profile.profilePictureUrl}" />
		<div class="clear"></div>
		
		<h3>Task Lists</h3>
		<ul>
			<c:forEach items="${taskLists}" var="taskList">
				<li>${taskList.title}</li>
			</c:forEach>
		</ul>
		
		<h3>Google Contacts</h3>
		<table>
			<c:forEach items="${contacts}" var="contact">
				<tr>
					<td>${contact.name}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
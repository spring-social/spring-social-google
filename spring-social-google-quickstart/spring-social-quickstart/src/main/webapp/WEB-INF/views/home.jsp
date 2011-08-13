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
	</div>
</body>
</html>
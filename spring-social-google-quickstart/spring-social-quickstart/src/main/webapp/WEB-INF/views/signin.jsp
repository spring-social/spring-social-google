<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Sign In</title>
	</head>
	<body>
		<form action="<c:url value="/signin/google" />" method="POST">
		    <button type="submit">Sign in with Google</button>
		    <input type="hidden" name="scope" value="https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo#email https://www.google.com/m8/feeds https://www.googleapis.com/auth/tasks" />		    
		</form>
	</body>
</html>

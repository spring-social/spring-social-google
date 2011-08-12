<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Sign In</title>
	</head>
	<body>
		<form action="<c:url value="/signin/google" />" method="POST">
		    <button type="submit">Sign in with Google</button>
		    <input type="hidden" name="scope" value="https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo#email" />		    
		</form>
	</body>
</html>

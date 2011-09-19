<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<jsp:text>
		<![CDATA[ <!DOCTYPE html> ]]>
	</jsp:text>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<title>Spring Social Google - Contacts</title>
<jsp:directive.include file="header.jspf" />
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="span14 columns offset2">
				<h1>Spring Social Google Example Application</h1>
				<h5>Sources: <a href="https://github.com/guznik/spring-social-google/">https://github.com/guznik/spring-social-google/</a></h5>
				<form action="signin/google" method="POST">
				    <button type="submit" class="btn large primary">Sign in with Google</button>
				    <input type="hidden" name="scope" value="https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo#email https://www.googleapis.com/auth/plus.me https://www.google.com/m8/feeds" />		    
				</form>
			</div>
		</div>
	</div>
</body>
</html>
</jsp:root>
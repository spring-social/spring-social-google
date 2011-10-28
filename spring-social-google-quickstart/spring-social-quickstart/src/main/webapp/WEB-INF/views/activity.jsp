<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:form="http://www.springframework.org/tags/form">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<jsp:text>
		<![CDATA[ <!DOCTYPE html> ]]>
	</jsp:text>
	<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<title>Spring Social Google - Activity</title>
<jsp:directive.include file="header.jspf" />
</head>

<body>
	<c:set var="selected" value="plus" />
	<jsp:directive.include file="bar.jspf" />

	<div class="container-fluid">
	
		<jsp:directive.include file="plusops.jspf" />
		
		<div class="content">

			<h1>Activities</h1>
			
			<jsp:directive.include file="activitycontent.jspf"/>
		</div>
	</div>
</body>
</html>
</jsp:root>
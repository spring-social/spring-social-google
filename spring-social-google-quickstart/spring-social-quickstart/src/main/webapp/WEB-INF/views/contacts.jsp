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
	<c:set var="selected" value="contacts"/>
	<jsp:directive.include file="bar.jspf"/>

	<div class="container">

		<h1>Contacts and Groups</h1>

		<div class="row">
			<div class="span4 columns">
				<h3>Contact Groups</h3>
				<ul class="unstyled">
					<li><a href="?">All Contacts</a></li>
					<c:forEach items="${groups}" var="group">
						<li><a href="?group=${group.id}">${group.name}</a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="span12 columns">
			<h3>Contacts</h3>
				<table>
					<thead>
						<tr>
							<th>Name</th>
							<th>E-mail</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${contacts}" var="contact">
							<tr>
								<td>${contact.name}</td>
								<td>${contact.email}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
	</html>
</jsp:root>
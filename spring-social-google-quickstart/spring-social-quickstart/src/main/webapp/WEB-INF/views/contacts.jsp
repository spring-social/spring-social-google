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
	<c:set var="selected" value="contacts" />
	<jsp:directive.include file="bar.jspf" />

	<div class="container">

		<h1>Contacts and Groups</h1>

		<div class="row">
			<div class="span4 columns">
				<h3>Contact Groups</h3>
				<a href="group" class="btn success">New Group</a>
				<table>
					<tr>
						<td><a href="?">All Contacts</a>
						</td>
						<td></td>
					</tr>
					<c:forEach items="${groups}" var="group">
						<tr>
							<td><a href="?group=${group.id}">${group.name}</a></td>
							<td><a href="group?url=${group.self}">Edit</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="span12 columns">
				<h3>Contacts</h3>
				<a href="contact" class="btn success">New Contact</a>
				<table>
					<thead>
						<tr>
							<th>Name</th>
							<th>E-mail</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${contacts}" var="contact">
							<tr>
								<td>${contact.fullName}</td>
								<td>${contact.primaryEmailAddress}</td>
								<td><a href="contact?url=${contact.self}">Edit</a></td>
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
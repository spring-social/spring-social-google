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
<title>Spring Social Google - People</title>
<jsp:directive.include file="header.jspf" />
</head>

<body>
	<c:set var="selected" value="plus" />
	<jsp:directive.include file="bar.jspf" />

	<div class="container-fluid">
	
		<jsp:directive.include file="plusops.jspf" />
		
		<div class="content">

			<h1>People</h1>
			
			<jsp:directive.include file="peopleform.jspf"/>
			
			<c:if test="${not empty people.items}">
				<table class="zebra-striped">
					<thead>
						<th>Name</th>
						<th></th>
						<td></td>
					</thead>
					<tbody>
						<c:forEach items="${people.items}" var="person">
							<tr>
								<td>${person.displayName}</td>
								<td><a href="person?id=${person.id}">Profile</a></td>
								<td><a href="activities?person=${person.id}">Activities</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<c:if test="${not empty people.nextPageToken}">
					<p class="pull-right"><a href="people?text=${param.text}&amp;pageToken=${people.nextPageToken}"><![CDATA[Next Page &rarr;]]></a></p>
				</c:if>
			</c:if>
			<c:if test="${(empty people.items) and (not empty param.text)}">
				<div>No people were found for search criteria</div>
			</c:if>
		</div>
	</div>
</body>
</html>
</jsp:root>
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
	<c:set var="subselected" value="people" />
	<jsp:directive.include file="bar.jspf" />

	<div class="container">
	
		<jsp:directive.include file="plusops.jspf" />
	
		<h1>People</h1>
		
		<jsp:directive.include file="peopleform.jspf"/>
		
		<c:if test="${not empty people.items}">
			<table class="table table-hover">
				<thead>
					<th>Name</th>
					<th></th>
					<th></th>
					<th></th>
				</thead>
				<tbody>
					<c:forEach items="${people.items}" var="person">
						<tr>
							<td width="100%">${person.displayName}</td>
							<td>
								<c:if test="${person.contactWithProfile}">
									<a href="person?contact=${person.id}">Contact</a>
								</c:if>
							</td>
							<td>
								<c:if test="${person.googlePlusProfile}">
									<a href="person?id=${person.id}">Profile</a>
								</c:if>
							</td>
							<td>
								<c:if test="${person.googlePlusProfile}">
									<a href="activities?person=${person.id}">Activities</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${not empty people.nextPageToken}">
				<p class="pull-right"><a href="people?text=${param.text}&amp;group=${param.group}&amp;pageToken=${people.nextPageToken}"><![CDATA[Next Page &rarr;]]></a></p>
			</c:if>
		</c:if>
		<c:if test="${(empty people.items) and (not empty param.text)}">
			<div>No people were found for search criteria</div>
		</c:if>
	</div>
</body>
</html>
</jsp:root>
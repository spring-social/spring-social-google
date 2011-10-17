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
<title>Spring Social Google - Contact Groups</title>
<jsp:directive.include file="header.jspf" />
</head>

<body>
	<c:set var="selected" value="contacts" />
	<jsp:directive.include file="bar.jspf" />

	<div class="container-fluid">
	
		<jsp:directive.include file="contactops.jspf" />
		
		<div class="content">

			<h1>My Contact Groups</h1>
			
			<div>
			
				<form:form method="get">
					<div class="clearfix">
						<label for="updatedMin">Updated from:</label>
						<div class="input">
							<form:input path="updatedMin" cssClass="dateInput" />
						</div> 
					</div>
					<div class="clearfix">
						<label for="updatedMax">Updated until:</label>
						<div class="input">
							<form:input path="updatedMax" cssClass="dateInput" />
						</div> 
					</div>
					<div class="input">
						<input type="submit" class="btn" value="Search"/>
						<a href="group" class="btn success leftMargin">New Contact Group</a>
					</div>
				</form:form>
			
			</div>
			
			<c:if test="${groups.total gt 0}">
				<table class="zebra-striped">
					<thead>
						<th>Group Name</th>
						<th>Updated</th>
						<th></th>
					</thead>
					<tbody>
						<c:forEach items="${groups.items}" var="group">
							<tr>
								<td>${group.name}</td>
								<td>${group.updated}</td>
								<td><a href="group?url=${group.self}">Edit</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<c:set var="page" value="${groups}"/>
				<jsp:directive.include file="pagination.jspf" />
			</c:if>
			<c:if test="${groups.total eq 0}">
				<div>No contact groups were found</div>
			</c:if>
		</div>
	</div>
</body>
</html>
</jsp:root>
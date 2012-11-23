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
<title>Spring Social Google - Task Lists</title>
<jsp:directive.include file="header.jspf" />
</head>

<body>
	<c:set var="selected" value="tasks" />
	<c:set var="subselected" value="tasklists" />
	<jsp:directive.include file="bar.jspf" />

	<div class="container">
	
		<jsp:directive.include file="taskops.jspf" />
		
		<h1>Task Lists</h1>
		
		<p>
			<a href="tasklist" class="btn btn-success">New Task List</a>
		</p>
		
		<c:if test="${not empty taskLists.items}">
			<table class="table table-hover">
				<thead>
					<th>Task List</th>
					<th></th>
					<th></th>
				</thead>
				<tbody>
					<c:forEach items="${taskLists.items}" var="taskList">
						<tr>
							<td width="100%">${taskList.title}</td>
							<td><a href="tasklist?id=${taskList.id}">Edit</a></td>
							<td><a href="tasks?list=${taskList.id}">Tasks</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${not empty taskLists.nextPageToken}">
				<p class="pull-right"><a href="tasklists?text=${param.text}&amp;pageToken=${tasklists.nextPageToken}"><![CDATA[Next Page &rarr;]]></a></p>
			</c:if>
		</c:if>
		<c:if test="${empty taskLists.items}">
			<div>No task lists were found</div>
		</c:if>
	</div>
</body>
</html>
</jsp:root>
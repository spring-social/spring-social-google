<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:spring="http://www.springframework.org/tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<jsp:text>
		<![CDATA[ <!DOCTYPE html> ]]>
	</jsp:text>
	<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<title>Spring Social Google - Task</title>
<jsp:directive.include file="header.jspf" />
</head>

<body>
	<c:set var="selected" value="tasks" />
	<c:set var="subselected" value="tasks" />
	<jsp:directive.include file="bar.jspf" />

	<div class="container">
	
		<jsp:directive.include file="taskops.jspf" />
		
		<h1>Task Details</h1>

		<form:form cssClass="form-horizontal">
			<div class="control-group">
				<label for="title" class="control-label">Name</label>
				<div class="controls">
					<form:input path="title" cssClass="input-xlarge" />
				</div>
			</div>
			<div class="control-group">
				<label for="notes" class="control-label">Notes</label>
				<div class="controls">
					<form:textarea path="notes" cssClass="input-xlarge" />
				</div>
			</div>
			<div class="control-group">
				<label for="due" class="control-label">Due</label>
				<div class="controls">
					<form:input path="due" cssClass="dateInput input-small" />
				</div>
			</div>
			<div class="control-group">
				<label for="completed" class="control-label">Completed</label>
				<div class="controls">
					<form:input path="completed" cssClass="dateInput input-small" />
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<input type="submit" class="btn btn-primary" value="Save" />
					<a href="tasks?list=${param.list}" class="btn leftMargin">Cancel</a>
					<c:if test="${param.id != null}">
						<input name="delete" type="submit" class="btn btn-danger leftMargin" value="Delete" 
							onclick="return confirm('Are you sure you want to delete this task?')" />
					</c:if>
				</div>
			</div>
			<spring:hasBindErrors name="taskListForm">
				<div class="error">
					<c:forEach items="${errors.allErrors}" var="error">
						<div><span class="help-inline"><spring:message message="${error}" /></span></div>
					</c:forEach>
				</div>
			</spring:hasBindErrors>
		</form:form>
	</div>
</body>
	</html>
</jsp:root>
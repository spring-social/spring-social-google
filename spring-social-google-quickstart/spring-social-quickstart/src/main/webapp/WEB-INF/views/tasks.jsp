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
	<title>Spring Social Google - Tasks</title>
	<jsp:directive.include file="header.jspf" />
	<script src="resources/js/jquery.treeTable.min.js"><![CDATA[<!-- -->]]></script>
</head>

<body>
	<c:set var="selected" value="tasks" />
	<c:set var="subselected" value="tasks" />
	<jsp:directive.include file="bar.jspf" />

	<div class="container">
	
		<jsp:directive.include file="taskops.jspf" />
		
		<c:if test="${empty param.move}">
			<h1>Tasks</h1>
			<div>
				<div>
					<form:form method="get" cssClass="form-horizontal">
						<form:hidden path="list" />
						<div class="control-group">
							<label for="dueMin" class="control-label">Due from</label>
							<div class="controls">
								<form:input path="dueMin" cssClass="dateInput input-small"/>
							</div>
						</div>
						<div class="control-group">
							<label for="dueMax" class="control-label">Due until</label>
							<div class="controls">
								<form:input path="dueMax" cssClass="dateInput input-small"/>
							</div>
						</div>
						<div class="control-group">
							<label for="completedMin" class="control-label">Completed from</label>
							<div class="controls">
								<form:input path="completedMin" cssClass="dateInput input-small"/>
							</div>
						</div>
						<div class="control-group">
							<label for="completedMax" class="control-label">Completed until</label>
							<div class="controls">
								<form:input path="completedMax" cssClass="dateInput input-small"/>
							</div>
						</div>
						<div class="control-group">
							<label for="updatedMin" class="control-label">Updated from</label>
							<div class="controls">
								<form:input path="updatedMin" cssClass="dateInput input-small"/>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<label class="checkbox inline">
									<form:checkbox path="includeCompleted"/>
									Include completed
								</label>
								<label class="checkbox inline">
									<form:checkbox path="includeDeleted"/>
									Include deleted
								</label>
								<label class="checkbox inline">
									<form:checkbox path="includeHidden"/>
									Include hidden
								</label>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<input type="submit" class="btn" value="Search"/>
							</div>
						</div>
					</form:form>
				</div>
				<div>
					<a href="task?list=${param.list}" class="btn btn-success leftMargin">New Task</a>
					<form action="cleartasks?list=${param.list}" method="post" class="inline" 
						onsubmit="return confirm('Are you sure you want to clear the completed tasks?')">
						<input type="submit" class="btn btn-danger leftMargin" value="Clear Completed Tasks"/>
					</form>
				</div>
			</div>
		</c:if>
		
		<c:if test="${not empty param.move}">
			<h1>Move Task</h1>
			<p>Select a new parent or position for the task</p>
		</c:if>
		
		<br/>
		
		<div>
			<c:if test="${not empty tasks.items}">
				<table id="tasks" class="table table-hover">
					<thead>
						<th>Task</th>
						<c:if test="${empty param.move}">
							<th>Due</th>
							<th>Status</th>
							<th></th>
							<th></th>
						</c:if>
						<th></th>
						<th></th>
					</thead>
					<tbody>
						<c:forEach items="${tasks.items}" var="task">
							<c:if test="${not empty task.parent}">
								<c:set var="nodeClass" value="child-of-${task.parent}"/>
							</c:if>
							<tr id="${task.id}" class="${nodeClass}">
								<td>${task.title}</td>
								<c:if test="${empty param.move}">
									<td>${task.due}</td>
									<td>${task.status}</td>
									<td><a href="task?list=${param.list}&amp;id=${task.id}">Edit</a></td>
									<td><a href="tasks?list=${param.list}&amp;move=${task.id}">Move</a></td>
									<td><a href="task?list=${param.list}&amp;parent=${task.id}">Insert Sub-Task</a></td>
									<td><a href="task?list=${param.list}&amp;parent=${task.parent}&amp;previous=${task.id}">Append Task</a></td>
								</c:if>
								<c:if test="${not empty param.move}">
									<c:if test="${task.id eq param.move}">
										<td></td>
										<td></td>
									</c:if>
									<c:if test="${task.id ne param.move}">
										<td>
											<form action="movetask?list=${param.list}&amp;move=${param.move}&amp;parent=${task.id}" method="post" class="inline">
												<input type="submit" value="Select Parent" class="btn"/>
											</form>
										</td>
										<td>
											<form action="movetask?list=${param.list}&amp;move=${param.move}&amp;parent=${task.parent}&amp;previous=${task.id}" method="post" class="inline">
												<input type="submit" value="Select Previous" class="btn"/>
											</form>
										</td>
									</c:if>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<script>
					$("#tasks").treeTable({expandable: false});
				</script>
				<c:if test="${not empty tasks.nextPageToken}">
					<p class="pull-right"><a href="tasks?list=${param.list}&amp;pageToken=${tasklists.nextPageToken}"><![CDATA[Next Page &rarr;]]></a></p>
				</c:if>
			</c:if>
			<c:if test="${empty tasks.items}">
				<div>No tasks were found</div>
			</c:if>
			<c:if test="${not empty param.move}">
				<p>
					<a href="tasks?list=${param.list}" class="btn">Cancel</a>
				</p>
			</c:if>
		</div>
	</div>
</body>
</html>
</jsp:root>
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
<title>Spring Social Google - Drive Files</title>
<jsp:directive.include file="header.jspf" />
<link href="resources/css/silk-sprite.css" rel="stylesheet"/>
<script src="resources/js/bootbox.min.js"><![CDATA[<!-- -->]]></script>
<script src="resources/js/drive.js"><![CDATA[<!-- -->]]></script>
</head>

<body>
	<c:set var="selected" value="drive" />
	<jsp:directive.include file="bar.jspf" />

	<div class="container">
	
		<div class="content">

			<h1>Drive</h1>
			
			<!-- <p>
				<a href="tasklist" class="btn success leftMargin">New Task List</a>
			</p> -->
			
			<form:form method="get" cssClass="form-horizontal">
				<form:hidden path="parentId" />
				<div class="control-group">
					<div class="controls">
						<label class="checkbox">
							<form:checkbox path="negate"/> Negate query
						</label>
					</div>
				</div>
				<div class="control-group">
					<label for="titleIs" class="control-label">Title is</label>
					<div class="controls">
						<form:input path="titleIs" cssClass="input-xxlarge"/>
					</div>
				</div>
				<div class="control-group">
					<label for="titleContains" class="control-label">Title contains</label>
					<div class="controls">
						<form:input path="titleContains" cssClass="input-xxlarge"/>
					</div>
				</div>
				<div class="control-group">
					<label for="fullTextContains" class="control-label">Full text contains</label>
					<div class="controls">
						<form:input path="fullTextContains" cssClass="input-xxlarge"/>
					</div>
				</div>
				<div class="control-group">
					<label for="mimeTypeIs" class="control-label">Mime type is</label>
					<div class="controls">
						<form:input path="mimeTypeIs" cssClass="input-xxlarge"/>
					</div>
				</div>
				<div class="control-group">
					<label for="modifiedOperator" class="control-label">Modified</label>
					<div class="controls">
						<form:select path="modifiedOperator" items="${dateOperators}" cssClass="input-medium"></form:select>
						<form:input path="modifiedValue" cssClass="input-small dateInput"/>
					</div>
				</div>
				<div class="control-group">
					<label for="modifiedOperator" class="control-label">Last viewed by me</label>
					<div class="controls">
						<form:select path="lastViewedOperator" items="${dateOperators}" cssClass="input-medium"></form:select>
						<form:input path="lastViewedValue" cssClass="input-small dateInput"/>
					</div>
				</div>
				<div class="control-group">
					<label for="trashed" class="control-label">Trashed</label>
					<div class="controls">
						<form:select path="trashed" items="${booleanOperators}" cssClass="input-small"></form:select>
					</div>
				</div>
				<div class="control-group">
					<label for="starred" class="control-label">Starred</label>
					<div class="controls">
						<form:select path="starred" items="${booleanOperators}" cssClass="input-small"></form:select>
					</div>
				</div>
				<div class="control-group">
					<label for="hidden" class="control-label">Hidden</label>
					<div class="controls">
						<form:select path="hidden" items="${booleanOperators}" cssClass="input-small"></form:select>
					</div>
				</div>
				<div class="control-group">
					<label for="owner" class="control-label">Owner</label>
					<div class="controls">
						<form:input path="owner" cssClass="input-xxlarge"/>
					</div>
				</div>
				<div class="control-group">
					<label for="writer" class="control-label">Writer</label>
					<div class="controls">
						<form:input path="writer" cssClass="input-xxlarge"/>
					</div>
				</div>
				<div class="control-group">
					<label for="reader" class="control-label">Reader</label>
					<div class="controls">
						<form:input path="reader" cssClass="input-xxlarge"/>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<label class="checkbox">
							<form:checkbox path="sharedWithMe"/> Shared with me
						</label>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<input type="submit" class="btn" value="Search"/>
					</div>
				</div>
			</form:form>
			
			<c:if test="${not empty param.parentId and param.parentId ne 'root'}">
				<a href="?parentId=root"><![CDATA[&larr; Root Folder]]></a>
			</c:if>
			
			<c:if test="${not empty files.items}">
				<table class="table table-hover">
					<thead>
						<th></th>
						<th colspan="2">File Name</th>
						<th></th>
						<th></th>
						<th></th>
					</thead>
					<tbody>
						<c:forEach items="${files.items}" var="file">
							<tr file-id="${file.id}" file-name="${file.title}">
								<td width="16">
									<a href="javascript:void(0)" class="star ui-silk ${file.starred ? '' : 'gray'} ui-silk-star" title="${file.starred ? 'Unstar' : 'Star'}"><!--  --></a>
								</td>
								<td width="16">
									<script>
										var icon = getIcon('${file.mimeType}');
										document.write('<span class="ui-silk ui-silk-' + icon + '"><!--  --></span> ');
									</script>
								</td>
								<td class="name-cell ${file.viewed ? '' : 'unviewed'} ${file.trashed ? 'trashed' : ''}">
									<c:if test="${file.folder}">
										<a href="?parentId=${file.id}">${file.title}</a>
									</c:if>
									<c:if test="${not file.folder}">
										${file.title}
									</c:if>
								</td>
								<td>
									<a href="javascript:void(0)" class="trash ui-silk ui-silk-${file.trashed ? 'arrow-turn-left' : 'bin'}" title="${file.trashed ? 'Untrash' : 'Trash'}"><![CDATA[<!-- -->]]></a>
								</td>
								<td><a href="javascript:void(0)" class="delete ui-silk ui-silk-cross" title="Delete"><![CDATA[<!-- -->]]></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<c:if test="${not empty files.nextPageToken}">
					<p class="pull-right"><a href="drivefiles?text=${param.text}&amp;pageToken=${files.nextPageToken}"><![CDATA[Next Page &rarr;]]></a></p>
				</c:if>
			</c:if>
			<c:if test="${empty files.items}">
				<div>No files were found</div>
			</c:if>
		</div>
	</div>
</body>
</html>
</jsp:root>
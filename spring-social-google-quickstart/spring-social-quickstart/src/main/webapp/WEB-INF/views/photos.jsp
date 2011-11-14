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
<title>Spring Social Google - Albums</title>
<jsp:directive.include file="header.jspf" />
</head>

<body>
	<c:set var="selected" value="picasa" />
	<jsp:directive.include file="bar.jspf" />

	<div class="container">

		<h1>Picasa Web Albums</h1>

		<div>
			<form:form method="get">
				<div class="pull-left">
					<div class="clearfix">
						<label for="text">Search for:</label>
						<div class="input">
							<form:input path="text"/>
						</div>
					</div>
					<div class="clearfix">
						<label for="location">From location:</label>
						<div class="input">
							<form:input path="location"/>
						</div>
					</div>
					<div class="clearfix">
						<label for="boundingBox">Bounding coordinates:</label>
						<div class="input">
							<form:input path="west" cssClass="span2"/>
							<form:input path="south" cssClass="span2"/>
							<form:input path="east" cssClass="span2"/>
							<form:input path="north" cssClass="span2"/>
						</div>
					</div>
					<div class="clearfix">
						<label for="maxSize">Maximum Size:</label>
						<div class="input">
							<form:select path="maxSize">
								<option></option>
								<option></option>
							</form:select>
						</div>
					</div>
					<div class="clearfix">
						<label for="thumbSize">Thumbnail Size:</label>
						<div class="input">
							<form:select path="thumbSize">
								<option></option>
								<option></option>
							</form:select>
						</div>
					</div>
				</div>
				<div class="pull-left">
					<div class="clearfix">
						<label for="access">Access:</label>
						<div class="input">
							<form:select path="access">
								<form:option value="ALL">All</form:option>
								<form:option value="PRIVATE">Private</form:option>
								<form:option value="PUBLIC">Public</form:option>
								<form:option value="VISIBLE">Visible</form:option>
							</form:select>
						</div>
					</div>
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
					<div class="clearfix">
						<label for="publishedMin">Published from:</label>
						<div class="input">
							<form:input path="publishedMin" cssClass="dateInput" />
						</div> 
					</div>
					<div class="clearfix">
						<label for="publishedMax">Published until:</label>
						<div class="input">
							<form:input path="publishedMax" cssClass="dateInput" />
						</div> 
					</div>
				</div>
				<div class="input">
					<input type="submit" value="Search" class="btn"/>
					<a href="album" class="btn success leftMargin">New Album</a>
				</div>
			</form:form>
		</div>

		<div>
			<c:if test="${photos.total gt 0}">
				<ul class="media-grid">
					<c:forEach items="${photos.items}" var="photo">
						<li>
							<a href="album?id=${photo.id}">
								<img class="thumbnail" src="${photo.thumbnailUrl}"/>
								<br/>
								<strong>${photo.title}</strong>
							</a>
						</li>
					</c:forEach>
				</ul>
				<c:set var="page" value="${photos}"/>
				<jsp:directive.include file="pagination.jspf" />
			</c:if>
			<c:if test="${photos.total eq 0}">
				<div>No photos were found</div>
			</c:if>
		</div>
	</div>
</body>
	</html>
</jsp:root>
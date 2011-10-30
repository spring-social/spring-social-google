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
<title>Spring Social Google - Album</title>
<jsp:directive.include file="header.jspf" />
</head>

<body>
	<c:set var="selected" value="picasa" />
	<jsp:directive.include file="bar.jspf" />

	<div class="container">

		<h1>Picasa Web Album</h1>

		<div class="row">

			<div class="span10 columns">

				<form:form action="album">
					<form:hidden path="id" />
					<div class="clearfix">
						<label for="title">Title:</label>
						<div class="input">
							<form:input path="title" cssClass="xlarge" />
						</div>
					</div>
					<div class="clearfix">
						<label for="summary">Summary:</label>
						<div class="input">
							<form:input path="summary" cssClass="xlarge" />
						</div>
					</div>
					<div class="clearfix">
						<label for="visibility">Visibility:</label>
						<div class="input">
							<form:select path="visibility">
								<form:option value="PRIVATE">Private</form:option>
								<form:option value="PROTECTED">Protected</form:option>
								<form:option value="PUBLIC">Public</form:option>
							</form:select>
						</div>
					</div>
					<c:if test="${album != null}">
						<div class="clearfix">
							<label>Author:</label>
							<div class="input">
								<a href="https://picasaweb.google.com/${album.authorId}" target="_blank">${album.authorName}</a>
							</div>
						</div>
						<div class="clearfix">
							<label>Photos:</label>
							<div class="input">
								<c:if test="${album.numberOfPhotos eq 0}">
									<span>No photos in album</span>
								</c:if>
								<c:if test="${album.numberOfPhotos gt 0}">
									<a href="photos?album=${album.id}">${album.numberOfPhotos} ${album.numberOfPhotos eq 1 ? 'Photo' : 'Photos'}</a>
								</c:if>
							</div>
						</div>
					</c:if>
					<div class="actions">
						<input type="submit" class="btn primary" value="Save" />
						<a href="albums" class="btn leftMargin">Cancel</a>
						<c:if test="${param.id != null}">
							<input name="delete" type="submit" class="btn danger secondary-action" value="Delete" 
								onclick="return confirm('Are you sure you want to delete this album?')" />
						</c:if>
					</div>
					<spring:hasBindErrors name="albumForm">
						<div class="error">
							<c:forEach items="${errors.allErrors}" var="error">
								<div><span class="help-inline"><spring:message message="${error}" /></span></div>
							</c:forEach>
						</div>
					</spring:hasBindErrors>
				</form:form>
			</div>
		</div>

	</div>
</body>
</html>
</jsp:root>
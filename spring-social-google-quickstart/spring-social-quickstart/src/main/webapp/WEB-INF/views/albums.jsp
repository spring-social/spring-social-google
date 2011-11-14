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

	<div class="container-fluid">

		<jsp:directive.include file="picasaops.jspf" />

		<div class="content">

			<h1>Albums</h1>
	
			<div>
				<form:form method="get">
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
					<div class="input">
						<input type="submit" value="Search" class="btn"/>
						<a href="album" class="btn success leftMargin">New Album</a>
					</div>
				</form:form>
			</div>
	
			<div>
				<c:if test="${albums.total gt 0}">
					<ul class="media-grid">
						<c:forEach items="${albums.items}" var="album">
							<li>
								<a href="album?id=${album.id}">
									<img class="thumbnail" src="${album.thumbnailUrl}"/>
									<br/>
									<strong>${album.title}</strong>
								</a>
							</li>
						</c:forEach>
					</ul>
					<c:set var="page" value="${albums}"/>
					<jsp:directive.include file="pagination.jspf" />
				</c:if>
				<c:if test="${albums.total eq 0}">
					<div>No albums were found</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>
</jsp:root>
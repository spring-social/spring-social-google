<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core">
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

		<ul class="media-grid">
			<c:forEach items="${albums}" var="album">
				<li>
					<a href="album?id=${album.id}">
						<img class="thumbnail" src="${album.thumbnailUrl}"/>
						<br/>
						<strong>${album.title}</strong>
					</a>
				</li>
			</c:forEach>
		</ul>

	</div>
</body>
	</html>
</jsp:root>
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
<title>Spring Social Google - Comments</title>
<jsp:directive.include file="header.jspf" />
</head>

<body>
	<c:set var="selected" value="plus" />
	<jsp:directive.include file="bar.jspf" />

	<div class="container-fluid">
	
		<jsp:directive.include file="plusops.jspf" />
		
		<div class="content">

			<h1>Comments</h1>
			
			<c:if test="${not empty comments.items}">

				<c:forEach items="${comments.items}" var="comment">
					<div>
						<jsp:directive.include file="commentcontent.jspf"/>
						<a href="comment?id=${comment.id}">Show comment by itself</a>
						<br/><br/>
					</div>
				</c:forEach>				

				<c:if test="${not empty comments.nextPageToken}">
					<p class="pull-right"><a href="comments?text=${param.text}&amp;pageToken=${comments.nextPageToken}"><![CDATA[Next Page &rarr;]]></a></p>
				</c:if>
			</c:if>
			<c:if test="${(empty comments.items) and (not empty param.text)}">
				<div>No comments were found for search criteria</div>
			</c:if>
		</div>
	</div>
</body>
</html>
</jsp:root>
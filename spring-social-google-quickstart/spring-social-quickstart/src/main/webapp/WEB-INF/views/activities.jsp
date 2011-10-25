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
<title>Spring Social Google - Activities</title>
<jsp:directive.include file="header.jspf" />
</head>

<body>
	<c:set var="selected" value="plus" />
	<jsp:directive.include file="bar.jspf" />

	<div class="container-fluid">
	
		<jsp:directive.include file="plusops.jspf" />
		
		<div class="content">

			<h1>Activities</h1>
			
			<jsp:directive.include file="activitiesform.jspf"/>
			
			<c:if test="${not empty activities.items}">

				<c:forEach items="${activities.items}" var="activity">
					<div class="activity">
						<a href="${activity.url}" target="_blank">
							<b>${activity.title}</b> <br/> published: ${activity.published} , updated: ${activity.updated}
						</a>
						<a href="${activity.actor.profileUrl}" target="_blank">${activity.actor.displayName}</a>
						<br/>
						<br/>
						${activity.content}
						<br/>
						<c:forEach items="${activity.attachments}" var="attachment">
							<br/>
							<c:if test="${attachment.url != null}">
								<a href="${attachment.url}" target="_blank">
									<b>${attachment.displayName}</b>
								</a>
							</c:if>
							<c:if test="${attachment.url == null}">
								<b>${attachment.displayName}</b>
							</c:if>
							<br/>
							${attachment.content}
							<br/>
							<c:if test="${attachment.previewImageUrl != null}">
								<c:if test="${attachment.url != null}">
									<a href="${attachment.url}" target="_blank">
										<img src="${attachment.previewImageUrl}" alt="${attachment.displayName}" />
									</a>
								</c:if>
								<c:if test="${attachment.url == null}">
									<img src="${attachment.previewImageUrl}" alt="${attachment.displayName}" />
								</c:if>
							</c:if>
							<br/>
						</c:forEach>
					</div>
				</c:forEach>				

				<c:if test="${not empty activities.nextPageToken}">
					<p class="pull-right"><a href="activities?text=${param.text}&amp;pageToken=${activities.nextPageToken}"><![CDATA[Next Page &rarr;]]></a></p>
				</c:if>
			</c:if>
			<c:if test="${(empty people.items) and (not empty param.text)}">
				<div>No activities were found for search criteria</div>
			</c:if>
		</div>
	</div>
</body>
</html>
</jsp:root>
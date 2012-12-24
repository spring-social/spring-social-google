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
<title>Spring Social Google - Profile</title>
<jsp:directive.include file="header.jspf" />
</head>

<body>
	<c:set var="selected" value="plus" />
	<c:set var="subselected" value="people" />
	<jsp:directive.include file="bar.jspf" />

	<div class="container">
	
		<jsp:directive.include file="plusops.jspf" />
		
		<h1>Profile</h1>
		
		<jsp:directive.include file="peopleform.jspf"/>
		
		<c:if test="${not empty person}">
			<div class="span12 columns">
				<form class="form-horizontal">
					<div class="control-group">
						<label class="control-label">Profile ID:</label>
						<div class="controls">
							<span class="uneditable-input input-xxlarge">${person.id}</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Display Name:</label>
						<div class="controls">
							<span class="uneditable-input input-xxlarge">${person.displayName}</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Gender:</label>
						<div class="controls">
							<span class="uneditable-input input-xxlarge">${person.gender}</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">About Me:</label>
						<div class="controls">
							<span class="uneditable-input input-xxlarge">${person.aboutMe}</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Relationship Status:</label>
						<div class="controls">
							<span class="uneditable-input input-xxlarge">${person.relationshipStatus}</span>
						</div>
					</div>
				</form>
			</div>
			<div class="span4 columns">
				<div>Profile Picture</div>
				<img src="${person.imageUrl}" />
			</div>
			<div class="clear"></div>
			<div>
				<h5>URLs</h5>
				<ul>
					<c:forEach items="${person.urls}" var="url">
						<li><a href="${url.key}">${url.key}</a> ${url.value}</li>
					</c:forEach>
				</ul>
			</div>
			<div>
				<h5>Organizations</h5>
				<ul>
					<c:forEach items="${person.organizations}" var="organization">
						<li>${organization}</li>
					</c:forEach>
				</ul>
			</div>
			<div>
				<h5>Places Lived</h5>
				<ul>
					<c:forEach items="${person.placesLived}" var="place">
						<li>${place.key}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		<c:if test="${empty person and not empty param.id}">
			<div>No profile was found for the provided ID</div>
		</c:if>
	</div>
</body>
</html>
</jsp:root>
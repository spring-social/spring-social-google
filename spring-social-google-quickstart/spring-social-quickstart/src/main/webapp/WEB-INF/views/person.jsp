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
	<jsp:directive.include file="bar.jspf" />

	<div class="container-fluid">
	
		<jsp:directive.include file="plusops.jspf" />
		
		<div class="content">

			<h1>Profile</h1>
			
			<jsp:directive.include file="peopleform.jspf"/>
			
			<c:if test="${not empty person}">
				<div class="span12 columns">
					<div class="clearfix">
						<a href="${person.profileUrl}" target="_blank">Google Profile Page</a>
					</div>
					<form>
						<div class="clearfix">
							<label>Profile ID:</label>
							<label class="text">${person.id}</label>
						</div>
						<div class="clearfix">
							<label>Display Name:</label>
							<label class="text">${person.displayName}</label>
						</div>
						<div class="clearfix">
							<label>Gender:</label>
							<label class="text">${person.gender}</label>
						</div>
						<div class="clearfix">
							<label>About Me:</label>
							<label class="text">${person.aboutMe}</label>
						</div>
						<div class="clearfix">
							<label>Relationship Status:</label>
							<label class="text">${person.relationshipStatus}</label>
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
							<li><a href="${url.value}">${url.value}</a> ${url.type}</li>
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
							<li>${place}</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
			<c:if test="${empty person and not empty param.id}">
				<div>No profile was found for the provided ID</div>
			</c:if>
		</div>
	</div>
</body>
</html>
</jsp:root>
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
<title>Spring Social Google - Google+</title>
<jsp:directive.include file="header.jspf" />
<jsp:directive.include file="jquery.jspf" />
</head>

<body>
	<c:set var="selected" value="plus" />
	<jsp:directive.include file="bar.jspf" />

	<div class="container">

		<h1>Google+</h1>

		<div class="row">

			<form action="plus">
				<input type="hidden" id="activities" name="activities"/>
				<div class="clearfix">
					<label for="id">Google User ID</label>
					<div class="input">
						<input type="text" name="id" class="xlarge" value="${empty param.id ? 'me' : param.id}"/>
						<span class="help-inline">Either a long number or "me"</span>
					</div>
					<div class="actions">
						<input type="submit" class="btn primary" value="Get Profile" onclick="$('#activities').val('')"/>
						<input type="submit" class="btn primary leftMargin" value="Get Activities" onclick="$('#activities').val('1')"/>
					</div>
				</div>
			</form>
				
			<c:if test="${profile != null}">
				<div class="span12 columns">
					<div class="clearfix">
						<a href="${profile.profileUrl}" target="_blank">Google Profile Page</a>
					</div>
					<div class="clearfix">
						<label>Profile ID:</label>
						<label class="text">${profile.id}</label>
					</div>
					<div class="clearfix">
						<label>Display Name:</label>
						<label class="text">${profile.displayName}</label>
					</div>
					<div class="clearfix">
						<label>Gender:</label>
						<label class="text">${profile.gender}</label>
					</div>
					<div class="clearfix">
						<label>About Me:</label>
						<label class="text">${profile.aboutMe}</label>
					</div>
					<div class="clearfix">
						<label>Relationship Status:</label>
						<label class="text">${profile.relationshipStatus}</label>
					</div>
				</div>
				<div class="span4 columns">
					<div>Profile Picture</div>
					<img src="${profile.imageUrl}" />
				</div>
			</c:if>
			
			<c:if test="${activitiesFeed != null}">
				<c:forEach items="${activitiesFeed.activities}" var="activity">
					<div>
						<a href="${activity.url}" target="_blank">
							<b>${activity.title}</b> <br/> published: ${activity.published} , updated: ${activity.updated}
						</a>
						<br/>
						<a href="${activity.actor.profileUrl}" target="_blank">${activity.actor.displayName}</a>
						<br/><br/>
					</div>
				</c:forEach>
			</c:if>
		</div>
	</div>
</body>
</html>
</jsp:root>
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
					<form>
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
					</form>
				</div>
				<div class="span4 columns">
					<div>Profile Picture</div>
					<img src="${profile.imageUrl}" />
				</div>
				<div class="clear"></div>
				<div>
					<h5>URLs</h5>
					<ul>
						<c:forEach items="${profile.urls}" var="url">
							<li><a href="${url.value}">${url.value}</a> ${url.type}</li>
						</c:forEach>
					</ul>
				</div>
				<div>
					<h5>Organizations</h5>
					<ul>
						<c:forEach items="${profile.organizations}" var="organization">
							<li>${organization}</li>
						</c:forEach>
					</ul>
				</div>
				<div>
					<h5>Places Lived</h5>
					<ul>
						<c:forEach items="${profile.placesLived}" var="place">
							<li>${place}</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
			
			<c:if test="${activitiesFeed != null}">
				<c:forEach items="${activitiesFeed.items}" var="activity">
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
			</c:if>
		</div>
	</div>
</body>
</html>
</jsp:root>
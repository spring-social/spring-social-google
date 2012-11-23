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
<title>Spring Social Google - Profile</title>
<jsp:directive.include file="header.jspf" />
</head>


<body>
	<c:set var="selected" value="profile"/>
	<jsp:directive.include file="bar.jspf"/>

	<div class="container">

		<h1>User Profile</h1>

		<div class="row">
			<div class="span6">
				<form class="form-horizontal">
					<div class="control-group">
						<div class="controls">
							<a href="${profile.link}" target="_blank">Google Profile Page</a>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Profile ID:</label>
						<div class="controls">
							<span class="uneditable-input input-large">${profile.id}</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">E-mail:</label>
						<div class="controls">
							<span class="uneditable-input input-large">${profile.email}</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Display Name:</label>
						<div class="controls">
							<span class="uneditable-input input-large">${profile.name}</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">First Name:</label>
						<div class="controls">
							<span class="uneditable-input input-large">${profile.firstName}</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Last Name:</label>
						<div class="controls">
							<span class="uneditable-input input-large">${profile.lastName}</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Gender:</label>
						<div class="controls">
							<span class="uneditable-input input-large">${profile.gender}</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">Locale:</label>
						<div class="controls">
							<span class="uneditable-input input-large">${profile.locale}</span>
						</div>
					</div>
				</form>
			</div>
			<div class="span6">
				<div>Profile Picture</div>
				<img src="${profile.profilePictureUrl}" />
			</div>
		</div>
	</div>
</body>
	</html>
</jsp:root>
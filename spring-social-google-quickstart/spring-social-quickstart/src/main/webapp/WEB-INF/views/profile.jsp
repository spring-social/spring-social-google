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
			<div class="span8 columns">
				<div class="clearfix">
					<a href="${profile.link}" target="_blank">Google Profile Page</a>
				</div>
				<form>
					<div class="clearfix">
						<label>Profile ID:</label>
						<div class="input">
							<span class="uneditable-input">${profile.id}</span>
						</div>
					</div>
					<div class="clearfix">
						<label>E-mail:</label>
						<div class="input">
							<span class="uneditable-input">${profile.email}</span>
						</div>
					</div>
					<div class="clearfix">
						<label>Display Name:</label>
						<div class="input">
							<span class="uneditable-input">${profile.name}</span>
						</div>
					</div>
					<div class="clearfix">
						<label>First Name:</label>
						<div class="input">
							<span class="uneditable-input">${profile.firstName}</span>
						</div>
					</div>
					<div class="clearfix">
						<label>Last Name:</label>
						<div class="input">
							<span class="uneditable-input">${profile.lastName}</span>
						</div>
					</div>
					<div class="clearfix">
						<label>Gender:</label>
						<div class="input">
							<span class="uneditable-input">${profile.gender}</span>
						</div>
					</div>
					<div class="clearfix">
						<label>Locale:</label>
						<div class="input">
							<span class="uneditable-input">${profile.locale}</span>
						</div>
					</div>
				</form>
			</div>
			<div class="span8 columns">
				<div>Profile Picture</div>
				<img src="${profile.profilePictureUrl}" />
			</div>
		</div>
	</div>
</body>
	</html>
</jsp:root>
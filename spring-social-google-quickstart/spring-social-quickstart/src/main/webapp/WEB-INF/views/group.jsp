<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:spring="http://www.springframework.org/tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<jsp:text>
		<![CDATA[ <!DOCTYPE html> ]]>
	</jsp:text>
	<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<title>Spring Social Google - Contact Group</title>
<jsp:directive.include file="header.jspf" />
</head>

<body>
	<c:set var="selected" value="contacts" />
	<jsp:directive.include file="bar.jspf" />

	<div class="container">

		<h1>Contact Group Details</h1>

		<div class="row">

			<div class="span10 columns">

				<form:form>
					<form:hidden path="id" />
					<div class="clearfix">
						<label for="name">Name</label>
						<div class="input">
							<form:input path="name" cssClass="xlarge" />
						</div>
					</div>
					<div class="actions">
						<input type="submit" class="btn primary" value="Save" />
						<a href="groups" class="btn leftMargin">Cancel</a>
						<c:if test="${param.url != null}">
							<input name="delete" type="submit" class="btn danger secondary-action" value="Delete" 
								onclick="return confirm('Are you sure you want to delete this group?')" />
						</c:if>
					</div>
					<spring:hasBindErrors name="contactGroupForm">
						<div class="error">
							<c:forEach items="${errors.allErrors}" var="error">
								<div><span class="help-inline"><spring:message message="${error}" /></span></div>
							</c:forEach>
						</div>
					</spring:hasBindErrors>
				</form:form>
			</div>
		</div>
	</div>
</body>
	</html>
</jsp:root>
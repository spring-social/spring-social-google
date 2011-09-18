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
<title>Spring Social Google - Contact</title>
<jsp:directive.include file="header.jspf" />
<jsp:directive.include file="jquery.jspf" />
<script src="resources/contact.js"><![CDATA[<!--  -->]]></script>
</head>

<body>
	<c:set var="selected" value="contacts" />
	<jsp:directive.include file="bar.jspf" />

	<div class="container">

		<h1>Contact Details</h1>

		<div class="row">

			<div class="span16 columns">
			
				<c:if test="${param.url != null}">
					<div class="right">
						<img src="contactpicture?url=${command.pictureUrl}"/>
						<form action="contactpicture" method="post" enctype="multipart/form-data">
							<input type="hidden" name="pictureUrl" value="${command.pictureUrl}"/>
							<input type="file" name="file"/>
							<input type="submit" class="btn" value="Upload Photo"/>
						</form>
					</div>
				</c:if>

				<form:form>
					<div class="left">
						<form:hidden path="id" />
						<div class="clearfix">
							<label for="name">Name Prefix</label>
							<div class="input">
								<form:input path="namePrefix" cssClass="xlarge" />
							</div>
						</div>
						<div class="clearfix">
							<label for="name">First Name</label>
							<div class="input">
								<form:input path="firstName" cssClass="xlarge" />
							</div>
						</div>
						<div class="clearfix">
							<label for="name">Middle Name</label>
							<div class="input">
								<form:input path="middleName" cssClass="xlarge" />
							</div>
						</div>
						<div class="clearfix">
							<label for="name">Last Name</label>
							<div class="input">
								<form:input path="lastName" cssClass="xlarge" />
							</div>
						</div>
						<div class="clearfix">
							<label for="name">Name Suffix</label>
							<div class="input">
								<form:input path="nameSuffix" cssClass="xlarge" />
							</div>
						</div>
					</div>
					<div class="clear"></div>
					<div class="clearfix">
						<label for="addEmailButton">E-mail</label>
						<div class="input">
							<table>
								<thead>
									<tr>
										<th>Type</th>
										<th>Label</th>
										<th>Address</th>
										<th>Primary</th>
									</tr>
								</thead>
								<tbody id="emails">
									<c:forEach items="${command.emails}" var="email" varStatus="status" >
										<c:set var="index" value="${status.index}"/>
										<tr>
											<td>
												<form:select path="emails[${index}].rel" onchange="setLabel(this)">
													<form:option value=""/>
													<form:option value="http://schemas.google.com/g/2005#home" label="Home"/>
													<form:option value="http://schemas.google.com/g/2005#work" label="Work"/>
													<form:option value="http://schemas.google.com/g/2005#other" label="Other"/>
												</form:select>
											</td>
											<td>
												<form:input cssClass="label" path="emails[${index}].label" disabled="${not empty email.rel}" />
											</td>
											<td>
												<form:input path="emails[${index}].address" />
											</td>
											<td>
												<form:checkbox path="emails[${index}].primary" onchange="setPrimary(this)"/>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="help-inline">Entries without an address and either a type or a label will be discarded</div>
							<input type="button" id="addEmailButton" class="btn" value="Add E-mail" onclick="addEmail()"/>
						</div>
					</div>
					<div class="clearfix">
						<label for="addPhoneButton">Phone</label>
						<div class="input">
							<table>
								<thead>
									<tr>
										<th>Type</th>
										<th>Label</th>
										<th>Number</th>
										<th>Primary</th>
									</tr>
								</thead>
								<tbody id="phones">
									<c:forEach items="${command.phones}" var="phone" varStatus="status" >
										<c:set var="index" value="${status.index}"/>
										<tr>
											<td>
												<form:select path="phones[${index}].rel" onchange="setLabel(this)">
													<form:option value=""/>
													<form:option value="http://schemas.google.com/g/2005#assistant" label="Assistant"/>
													<form:option value="http://schemas.google.com/g/2005#callback" label="Callback"/>
													<form:option value="http://schemas.google.com/g/2005#car" label="Car"/>
													<form:option value="http://schemas.google.com/g/2005#company_main" label="Company Main"/>
													<form:option value="http://schemas.google.com/g/2005#fax" label="Fax"/>
													<form:option value="http://schemas.google.com/g/2005#home" label="Home"/>
													<form:option value="http://schemas.google.com/g/2005#home_fax" label="Home Fax"/>
													<form:option value="http://schemas.google.com/g/2005#isdn" label="ISDN"/>
													<form:option value="http://schemas.google.com/g/2005#main" label="Main"/>
													<form:option value="http://schemas.google.com/g/2005#mobile" label="Mobile"/>
													<form:option value="http://schemas.google.com/g/2005#other" label="Other"/>
													<form:option value="http://schemas.google.com/g/2005#other_fax" label="Other Fax"/>
													<form:option value="http://schemas.google.com/g/2005#pager" label="Pager"/>
													<form:option value="http://schemas.google.com/g/2005#radio" label="Radio"/>
													<form:option value="http://schemas.google.com/g/2005#telex" label="Telex"/>
													<form:option value="http://schemas.google.com/g/2005#tty_tdd" label="TTY TDD"/>
													<form:option value="http://schemas.google.com/g/2005#work" label="Work"/>
													<form:option value="http://schemas.google.com/g/2005#work_fax" label="Work Fax"/>
													<form:option value="http://schemas.google.com/g/2005#work_mobile" label="Work Mobile"/>
													<form:option value="http://schemas.google.com/g/2005#work_pager" label="Work Pager"/>
												</form:select>
											</td>
											<td>
												<form:input cssClass="label" path="phones[${index}].label" disabled="${not empty phone.rel}" />
											</td>
											<td>
												<form:input path="phones[${index}].number" />
											</td>
											<td>
												<form:checkbox path="phones[${index}].primary" onchange="setPrimary(this)"/>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="help-inline">Entries without a number and either a type or a label will be discarded</div>
							<input type="button" id="addPhoneButton" class="btn" value="Add Phone" onclick="addPhone()"/>
						</div>
					</div>
					<div class="actions">
						<input type="submit" class="btn primary" value="Save" />
						<a href="contacts" class="btn">Cancel</a>
						<c:if test="${param.url != null}">
							<input name="delete" type="submit" class="btn danger secondary-action" value="Delete" 
								onclick="return confirm('Are you sure you want to delete this contact?')" />
						</c:if>
					</div>
					<spring:hasBindErrors name="contactForm">
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
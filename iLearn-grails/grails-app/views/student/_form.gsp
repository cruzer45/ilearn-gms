<%@ page import="ilearn.student.Student" %>



<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'socialSecurityNumber', 'error')} ">
	<label for="socialSecurityNumber">
		<g:message code="student.socialSecurityNumber.label" default="Social Security Number" />
		
	</label>
	<g:textField name="socialSecurityNumber" readonly="readonly" value="${studentInstance?.socialSecurityNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'firstName', 'error')} ">
	<label for="firstName">
		<g:message code="student.firstName.label" default="First Name" />
		
	</label>
	<g:textField name="firstName" readonly="readonly" value="${studentInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'lastName', 'error')} ">
	<label for="lastName">
		<g:message code="student.lastName.label" default="Last Name" />
		
	</label>
	<g:textField name="lastName" readonly="readonly" value="${studentInstance?.lastName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'otherNames', 'error')} ">
	<label for="otherNames">
		<g:message code="student.otherNames.label" default="Other Names" />
		
	</label>
	<g:textField name="otherNames" readonly="readonly" value="${studentInstance?.otherNames}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'dateOfBirth', 'error')} required">
	<label for="dateOfBirth">
		<g:message code="student.dateOfBirth.label" default="Date Of Birth" />
		<span class="required-indicator">*</span>
	</label>
	${studentInstance?.dateOfBirth?.toString()}
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'classCode', 'error')} ">
	<label for="classCode">
		<g:message code="student.classCode.label" default="Class Code" />
		
	</label>
	<g:textField name="classCode" readonly="readonly" value="${studentInstance?.classCode}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="student.status.label" default="Status" />
		
	</label>
	<g:textField name="status" readonly="readonly" value="${studentInstance?.status}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'photo', 'error')} required">
	<label for="photo">
		<g:message code="student.photo.label" default="Photo" />
		<span class="required-indicator">*</span>
	</label>
	<input type="file" id="photo" name="photo" />
</div>


<%@ page import="com.sportsday.Athlete" %>



<div class="fieldcontain ${hasErrors(bean: athleteInstance, field: 'country', 'error')} required">
	<label for="country">
		<g:message code="athlete.country.label" default="Country" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="country" name="country.id" from="${com.sportsday.Country.list()}" optionKey="id" optionValue="name" required="" value="${athleteInstance?.country?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: athleteInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="athlete.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${athleteInstance?.name}"/>
</div>


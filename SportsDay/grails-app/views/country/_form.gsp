<%@ page import="com.sportsday.Country" %>



<div class="fieldcontain ${hasErrors(bean: countryInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="country.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${countryInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: countryInstance, field: 'athletes', 'error')} ">
	<label for="athletes">
		<g:message code="country.athletes.label" default="Athletes" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${countryInstance?.athletes?}" var="a">
    <li><g:link controller="athlete" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="athlete" action="create" params="['country.id': countryInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'athlete.label', default: 'Athlete')])}</g:link>
</li>
</ul>

</div>


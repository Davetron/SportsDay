<%@ page import="com.sportsday.Event" %>



<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="event.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${eventInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'athletes', 'error')} ">
	<label for="athletes">
		<g:message code="event.athletes.label" default="Athletes" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${eventInstance?.athletes?}" var="a">
    <li><g:link controller="athlete" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="athlete" action="create" params="['event.id': eventInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'athlete.label', default: 'Athlete')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'mens', 'error')} ">
	<label for="mens">
		<g:message code="event.mens.label" default="Mens" />
		
	</label>
	<g:checkBox name="mens" value="${eventInstance?.mens}" />
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'time', 'error')} required">
	<label for="time">
		<g:message code="event.time.label" default="Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="time" precision="day"  value="${eventInstance?.time}"  />
</div>


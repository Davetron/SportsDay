<%@ page import="com.sportsday.Event" %>



<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'athletes', 'error')} ">
	<label for="athletes">
		<g:message code="event.athletes.label" default="Athletes" />
		
	</label>
	<g:select name="athletes" from="${com.sportsday.Athlete.list()}" multiple="multiple" optionKey="id" size="5" value="${eventInstance?.athletes*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'mens', 'error')} ">
	<label for="mens">
		<g:message code="event.mens.label" default="Mens" />
		
	</label>
	<g:checkBox name="mens" value="${eventInstance?.mens}" />
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="event.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${eventInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'time', 'error')} required">
	<label for="time">
		<g:message code="event.time.label" default="Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="time" precision="minute"  value="${eventInstance?.time}"  />
</div>


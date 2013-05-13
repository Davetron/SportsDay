<%@ page import="com.sportsday.Athlete" %>



<div class="fieldcontain ${hasErrors(bean: athleteInstance, field: 'country', 'error')} required">
	<label for="country">
		<g:message code="athlete.country.label" default="Country" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="country" name="country.id" from="${com.sportsday.Country.list()}" optionKey="id" required="" value="${athleteInstance?.country?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: athleteInstance, field: 'dnf', 'error')} ">
	<label for="dnf">
		<g:message code="athlete.dnf.label" default="Dnf" />
		
	</label>
	<g:checkBox name="dnf" value="${athleteInstance?.dnf}" />
</div>

<div class="fieldcontain ${hasErrors(bean: athleteInstance, field: 'event', 'error')} required">
	<label for="event">
		<g:message code="athlete.event.label" default="Event" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="event" name="event.id" from="${com.sportsday.Event.list()}" optionKey="id" required="" value="${athleteInstance?.event?.id}" class="many-to-one"/>
</div>


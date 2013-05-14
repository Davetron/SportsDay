package com.sportsday

import grails.converters.JSON

import org.springframework.dao.DataIntegrityViolationException

import com.sportsday.util.MapUtil

class EventController
{

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def overallStandings()
	{
		Map<String, Integer> overallStandings = new HashMap<String, Integer>(Country.count);

		boolean isMensEvent = Boolean.parseBoolean(params.mens)
		Event.findAllWhere(mens:isMensEvent).each
		{ event ->
			event.getAthletes().eachWithIndex
			{ athlete, i ->
				if (!athlete.dnf)
				{
					if (!overallStandings.containsKey(athlete.country))
					{
						Integer score = 13 - i;
						overallStandings.put(athlete.country, score)
					}
					else
					{
						Integer score = overallStandings.get(athlete.country) + 13 - i
						overallStandings.put(athlete.country, score);
					}
				}
			}
		}

		def results = MapUtil.sortByValue(overallStandings) 
		render results as JSON
	}
	
	
	def listEvents()
	{
		boolean isMensEvent = Boolean.parseBoolean(params.mens)
		//List<Event> results = Event.findAllWhere(mens:isMensEvent, sort:"time")
		def results = Event.findAll(sort:"time") {
			mens == isMensEvent
	   }
		render results as JSON
	}
	
	

	def index()
	{
		redirect(action: "list", params: params)
	}

	def list(Integer max)
	{
		params.max = Math.min(max ?: 10, 100)
		[eventInstanceList: Event.list(params), eventInstanceTotal: Event.count()]
	}

	def create()
	{
		[eventInstance: new Event(params)]
	}

	def save()
	{
		def eventInstance = new Event(params)
		if (!eventInstance.save(flush: true))
		{
			render(view: "create", model: [eventInstance: eventInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'event.label', default: 'Event'),
			eventInstance.id
		])
		redirect(action: "show", id: eventInstance.id)
	}

	def show(Long id)
	{
		def eventInstance = Event.get(id)
		if (!eventInstance)
		{
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'event.label', default: 'Event'),
				id
			])
			redirect(action: "list")
			return
		}

		[eventInstance: eventInstance]
	}

	def edit(Long id)
	{
		def eventInstance = Event.get(id)
		if (!eventInstance)
		{
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'event.label', default: 'Event'),
				id
			])
			redirect(action: "list")
			return
		}

		[eventInstance: eventInstance]
	}

	def update(Long id, Long version)
	{
		def eventInstance = Event.get(id)
		if (!eventInstance)
		{
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'event.label', default: 'Event'),
				id
			])
			redirect(action: "list")
			return
		}

		if (version != null)
		{
			if (eventInstance.version > version)
			{
				eventInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'event.label', default: 'Event')] as Object[],
						"Another user has updated this Event while you were editing")
				render(view: "edit", model: [eventInstance: eventInstance])
				return
			}
		}

		eventInstance.properties = params

		if (!eventInstance.save(flush: true))
		{
			render(view: "edit", model: [eventInstance: eventInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [
			message(code: 'event.label', default: 'Event'),
			eventInstance.id
		])
		redirect(action: "show", id: eventInstance.id)
	}

	def delete(Long id)
	{
		def eventInstance = Event.get(id)
		if (!eventInstance)
		{
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'event.label', default: 'Event'),
				id
			])
			redirect(action: "list")
			return
		}

		try
		{
			eventInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'event.label', default: 'Event'),
				id
			])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e)
		{
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'event.label', default: 'Event'),
				id
			])
			redirect(action: "show", id: id)
		}
	}
}

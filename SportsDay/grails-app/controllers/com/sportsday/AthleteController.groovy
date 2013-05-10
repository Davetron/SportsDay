package com.sportsday

import org.springframework.dao.DataIntegrityViolationException

class AthleteController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [athleteInstanceList: Athlete.list(params), athleteInstanceTotal: Athlete.count()]
    }

    def create() {
        [athleteInstance: new Athlete(params)]
    }

    def save() {
        def athleteInstance = new Athlete(params)
        if (!athleteInstance.save(flush: true)) {
            render(view: "create", model: [athleteInstance: athleteInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'athlete.label', default: 'Athlete'), athleteInstance.id])
        redirect(action: "show", id: athleteInstance.id)
    }

    def show(Long id) {
        def athleteInstance = Athlete.get(id)
        if (!athleteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'athlete.label', default: 'Athlete'), id])
            redirect(action: "list")
            return
        }

        [athleteInstance: athleteInstance]
    }

    def edit(Long id) {
        def athleteInstance = Athlete.get(id)
        if (!athleteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'athlete.label', default: 'Athlete'), id])
            redirect(action: "list")
            return
        }

        [athleteInstance: athleteInstance]
    }

    def update(Long id, Long version) {
        def athleteInstance = Athlete.get(id)
        if (!athleteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'athlete.label', default: 'Athlete'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (athleteInstance.version > version) {
                athleteInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'athlete.label', default: 'Athlete')] as Object[],
                          "Another user has updated this Athlete while you were editing")
                render(view: "edit", model: [athleteInstance: athleteInstance])
                return
            }
        }

        athleteInstance.properties = params

        if (!athleteInstance.save(flush: true)) {
            render(view: "edit", model: [athleteInstance: athleteInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'athlete.label', default: 'Athlete'), athleteInstance.id])
        redirect(action: "show", id: athleteInstance.id)
    }

    def delete(Long id) {
        def athleteInstance = Athlete.get(id)
        if (!athleteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'athlete.label', default: 'Athlete'), id])
            redirect(action: "list")
            return
        }

        try {
            athleteInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'athlete.label', default: 'Athlete'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'athlete.label', default: 'Athlete'), id])
            redirect(action: "show", id: id)
        }
    }
}

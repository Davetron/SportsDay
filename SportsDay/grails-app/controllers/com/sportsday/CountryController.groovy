package com.sportsday

import org.springframework.dao.DataIntegrityViolationException

class CountryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [countryInstanceList: Country.list(params), countryInstanceTotal: Country.count()]
    }

    def create() {
        [countryInstance: new Country(params)]
    }

    def save() {
        def countryInstance = new Country(params)
        if (!countryInstance.save(flush: true)) {
            render(view: "create", model: [countryInstance: countryInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'country.label', default: 'Country'), countryInstance.id])
        redirect(action: "show", id: countryInstance.id)
    }

    def show(Long id) {
        def countryInstance = Country.get(id)
        if (!countryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'country.label', default: 'Country'), id])
            redirect(action: "list")
            return
        }

        [countryInstance: countryInstance]
    }

    def edit(Long id) {
        def countryInstance = Country.get(id)
        if (!countryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'country.label', default: 'Country'), id])
            redirect(action: "list")
            return
        }

        [countryInstance: countryInstance]
    }

    def update(Long id, Long version) {
        def countryInstance = Country.get(id)
        if (!countryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'country.label', default: 'Country'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (countryInstance.version > version) {
                countryInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'country.label', default: 'Country')] as Object[],
                          "Another user has updated this Country while you were editing")
                render(view: "edit", model: [countryInstance: countryInstance])
                return
            }
        }

        countryInstance.properties = params

        if (!countryInstance.save(flush: true)) {
            render(view: "edit", model: [countryInstance: countryInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'country.label', default: 'Country'), countryInstance.id])
        redirect(action: "show", id: countryInstance.id)
    }

    def delete(Long id) {
        def countryInstance = Country.get(id)
        if (!countryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'country.label', default: 'Country'), id])
            redirect(action: "list")
            return
        }

        try {
            countryInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'country.label', default: 'Country'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'country.label', default: 'Country'), id])
            redirect(action: "show", id: id)
        }
    }
}

package com.sportsday

class Athlete {

    static constraints = {
    }

	static belongsTo = [country: Country]

	String name

}
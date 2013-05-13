package com.sportsday

class Athlete {

    static constraints = {
    }

	static belongsTo = [event: Event]

	boolean dnf = false
	Country country
	
	public String toString() {
		return country
	}

}
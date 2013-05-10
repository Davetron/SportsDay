package com.sportsday

class Event {

    static constraints = {
    }

	static hasMany = [athletes: Athlete]

	String name
	Calendar time = Calendar.getInstance()
	boolean mens
	List athletes

}
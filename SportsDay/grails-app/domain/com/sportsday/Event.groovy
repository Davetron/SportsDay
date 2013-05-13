package com.sportsday

class Event {

    static constraints = {
		name blank:false, unique: true
    }

	static hasMany = [athletes: Athlete]

	String name
	Calendar time = Calendar.getInstance()
	boolean mens
	List athletes
	
	public String toString() {
		return name + " " + athletes 
	}

}
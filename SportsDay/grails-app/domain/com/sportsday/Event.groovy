package com.sportsday

class Event {

    static constraints = {
		name (blank:false, unique: true)
		athletes (size: 0..12)
    }

	static hasMany = [athletes: Athlete]

	String name
	Calendar time = Calendar.getInstance()
	boolean mens
	List<Athlete>athletes
	
	public String toString() {
		return name 
	}

}
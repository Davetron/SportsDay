package com.sportsday

class Country {

    static constraints = {
		name unique:true
    }

	static hasMany = [athletes: Athlete]

	String name

}

package com.sportsday

class Country {

    static constraints = {
		name unique:true
		
    }

	String name
	
	public String toString() {
		return name;
	}

}

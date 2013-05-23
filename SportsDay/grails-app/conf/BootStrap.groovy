import com.sportsday.Athlete;
import com.sportsday.Country
import com.sportsday.Event

class BootStrap
{

	def init =
	{ servletContext ->

		if (!Country.count())
		{
			System.out.println("Iniitialising Countries")
			
			new Country(name: "Belgium", ).save(failOnError: true)
			new Country(name: "Bulgaria", ).save(failOnError: true)
			new Country(name: "Czech Republic", ).save(failOnError: true)
			new Country(name: "Estonia", ).save(failOnError: true)
			new Country(name: "Finland", ).save(failOnError: true)
			new Country(name: "Hungary", ).save(failOnError: true)
			new Country(name: "Ireland", ).save(failOnError: true)
			new Country(name: "Netherlands", ).save(failOnError: true)
			new Country(name: "Portugal", ).save(failOnError: true)
			new Country(name: "Romania", ).save(failOnError: true)
			new Country(name: "Sweden", ).save(failOnError: true)
			new Country(name: "Switzerland", ).save(failOnError: true)
		}

		if (!Event.count())
		{
			
			System.out.println("Iniitialising Events")
			String eventFile = "C:\\Users\\dconnolly.CELTICTIGER\\Documents\\athletics.txt"
			File file = new File(eventFile)
			
			if (file.exists())
			{
				file.splitEachLine(" ")
				{ tokens->

					Event day1Event = new Event(name: "", mens: false);
					Event day2Event = new Event(name: "", mens: false);

					boolean day1 = true
					tokens.eachWithIndex
					{ token, i ->

						if(token.matches("\\d+:\\d+") && i > 0)
						{
							// Hit the 2nd date token so we're onto day two
							day1 = false
						}

						// Set the event time
						if(token.matches("\\d+:\\d+"))
						{
							if (i > 0) day1 = false

							String[] time = token.split(":")
							int hour = Integer.parseInt(time[0])
							int mins = Integer.parseInt(time[1])

							if (day1)
							{
								Calendar cal1 = Calendar.getInstance()
								cal1.set(2013, Calendar.JUNE, 22, hour, mins, 0)
								day1Event.setTime(cal1)
							}
							else
							{
								Calendar cal2 = Calendar.getInstance();
								cal2.set(2013, Calendar.JUNE, 23, hour, mins, 0)
								day2Event.setTime(cal2)
							}
						}
						else
						{
							// Set event name
							if (day1 && token != null)
							{
								day1Event.name += token + " "
							}
							else
							{
								day2Event.name += token + " "
							}
						}
					}

					// Set mens/womens event & save
					if (!day1Event.name.isAllWhitespace()) {
						if (day1Event.name.contains("Men")) {
							day1Event.mens = true
						}
						day1Event.name = day1Event.name.trim()
						day1Event.save(failOnError: true)
					}

					if (!day2Event.name.isAllWhitespace()) {
						if (day2Event.name.contains("Men")) {
							day2Event.mens = true
						}
						day2Event.name = day2Event.name.trim()
						day2Event.save(failOnError: true)
					}
				}
			}
		}



		Event.all.each {event ->
			Country.all.each { country ->
				Athlete athlete = new Athlete(dnf: false, country: country)
				event.addToAthletes(athlete)
			}
			event.save(failOnError: true)
		}

		/*
		 def eventList = Event.list(sort: "time", order: "asc")
		 eventList.each {
		 System.out.println(it)
		 }
		 */
	}
	def destroy =
	{
	}
}

package edu.psu.se411.lab09_salaries;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import edu.psu.se411.lab09_salaries.exceptions.InvalidArgumentException;
import edu.psu.se411.lab09_salaries.model.FullTimeEmployee;

public class App {

	public static Logger logger = Logger.getLogger(App.class.getName());
	
	public static void main(String[] args) {
		logger.info("Application Salaries Management is starting...");
		
		try {
			FullTimeEmployee salah = new FullTimeEmployee("001", "Salah", "Obeid", 
					new GregorianCalendar(1980, Calendar.FEBRUARY, 11).getTime(),
					new GregorianCalendar(2022, Calendar.DECEMBER, 01).getTime(), -18000.0);
		} catch (InvalidArgumentException e) {
			logger.error(e.getMessage());
		}
		
		logger.info("Application Salaries Management is stopping...");
	}

}

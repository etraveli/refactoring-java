package formatter;

import java.util.Objects;

/**
 * @class Logger
 * 
 * MISSION: logger into STDOUT.
 */
public class SlipFormatter {

	private final String FORMAT_HEADER = "Rental Record for %s\n";
	private final String FORMAT_MOVIE = "\t%s\t%.1f\n";
	private final String FORMAT_FOOTER_AMOUNT = "Amount owed is %.1f\n";
	private final String FORMAT_FOOTER_FREQUENT_POINTS = "You earned %d frequent points\n";
	
	public static SlipFormatter instance;
	
	/*
	 * CONSTRUCTOR
	 */
	private SlipFormatter() {}

	/*
	 * PUBLIC METHODS
	 */
	public static SlipFormatter getInstance() {
		if( Objects.isNull(instance) ) {
			instance = new SlipFormatter();
		}
		
		return instance;
	}
	
	/***
	 * defines the format for the header.
	 */
	public String formatHeader( String name ) {
		return String.format(FORMAT_HEADER, name);
	}
	
	/***
	 * log the movie.
	 */
	public String formatMovie( String title, double amount ) {
		return String.format(FORMAT_MOVIE, title, amount);
	}
	
	/***
	 * defines the format for the footer in amount section.
	 */
	public String formatFooterAmount( double amount ) {
		return String.format(FORMAT_FOOTER_AMOUNT, amount);
	}
	
	/***
	 * defines the format for the footer in amount section.
	 */
	public String formatFooterFrequentPoints( int frequentEnterPoints ) {
		return String.format(FORMAT_FOOTER_FREQUENT_POINTS, frequentEnterPoints);
	}

}

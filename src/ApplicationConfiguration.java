/**
 * 
 */

/**
 * Application Configurations
 * <p>
 * <b> ofcourse we need agree on some naming conventions before hand but for now
 * going with whatever came on the spot<b>
 * 
 * @author WIN 8.1
 *
 */
public class ApplicationConfiguration {

	/**
	 * Constant for fix rate that may apply if regular movies rented upto or less
	 * that the default number of days
	 */
	public static final double FIXED_RATE_TILL_DEFAULT_DAYS_FOR_REGULAR_MOVIES = 2;

	/** Default day that user will be charged in any case for regular movies */
	public static final int DEFAULT_DAYS_FOR_REGULAR_MOVIES = 2;

	/** Per day charge after crossing the default days for regular movies */
	public static final double PER_DAY_RATE_AFTER_DEFAULT_DAYS_FOR_REGULAR_MOVIES = 1.5;

	/**
	 * Constant for fix rate that may apply if new movies rented upto or less that
	 * the default number of days
	 */
	public static final double FIXED_RATE_TILL_DEFAULT_DAYS_FOR_NEW_MOVIES = 0;

	/** Default day that user will be charged in any case for new movies */
	public static final int DEFAULT_DAYS_FOR_NEW_MOVIES = 0;

	/** Per day charge after crossing the default days for new movies */
	public static final double PER_DAY_RATE_AFTER_DEFAULT_DAYS_FOR_NEW_MOVIES = 3;

	/**
	 * Constant for fix rate that may apply if children movies rented upto or less
	 * that the default number of days
	 */
	public static final double FIXED_RATE_TILL_DEFAULT_DAYS_FOR_CHILDREN_MOVIES = 1.5;

	/** Default day that user will be charged in any case for children movies */
	public static final int DEFAULT_DAYS_FOR_CHILDREN_MOVIES = 3;

	/** Per day charge after crossing the default days for children movies */
	public static final double PER_DAY_RATE_AFTER_DEFAULT_DAYS_FOR_CHILDREN_MOVIES = 1.5;

	/**
	 * Constant indication a number of days for new movies after which client will
	 * get the frequent entering bonus points
	 */
	public static final int DEFAULT_DAYS_FOR_NEW_MOVIES_TO_GET_BONUS_POINTS = 2;
}
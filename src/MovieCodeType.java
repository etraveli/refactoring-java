/**
 * Create enum class for Movie code considering that it will have limited
 * values. If new type needs to be added you can anytime add it into this enum
 * and can start referencing it. It improves the readability and consistency.
 * <p>
 * 
 * @author WIN 8.1
 *
 */
public enum MovieCodeType {

	NEW("new"), 
	REGULAR("regular"),
	CHILDRENS("childrens");

	/** Value of the enum constant */
	private String value;

	MovieCodeType(String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

}

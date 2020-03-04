package entities;

public enum MovieCode {

	// COMMENT Introduce enum constants to limit inputs, group connected functions
	REGULAR("regular"),
	CHILDREN("children"),
	NEW("new");

	private final String displayName;

	MovieCode(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	// COMMENT Simplified the calculation formula for each ENUM
	public double getRentalRecordPrice(int days) {
		switch (this) {
		case REGULAR:
			return days > 2 ? ((days - 2) * 1.5) + 2 : 2;
		case CHILDREN:
			return days * 3;
		case NEW:
			return days > 3 ? ((days - 3) * 1.5) + 1.5 : 1.5;
		default:
			return 0;
		}
	}
}

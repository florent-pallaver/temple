package diet.model;

public enum Counting {

	GRAM,
	MILLILITER,
	UNIT,
	SPOON;
	
	public double getRatio() {
		switch (this) {
		case GRAM:
		case MILLILITER:
			return 100;
		default:
		}
		return 1;
	}
	
}

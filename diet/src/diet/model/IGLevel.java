package diet.model;

public enum IGLevel {

	UNKNOWN,
	LOW,
	MEDIUM,
	HIGH;

	public static IGLevel valueOf(int ig) {
		if(ig < 1 || ig > 100) {
			return UNKNOWN;
		}
		if(ig > 70) {
			return HIGH;
		}
		if(ig > 54) {
			return MEDIUM;
		}
		return LOW;
	}
	
}

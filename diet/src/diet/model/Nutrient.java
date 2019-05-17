package diet.model;

public interface Nutrient {

	double getProtein();

	double getFat();

	double getCarb();

	// Au moins 30g par jour???
	double getFiber();

	// 7kcal par gramme
	default double getAlcohol() {
		return 0;
	}
	
	int getKCal();
	
	Counting getCounting();
	
//	default int getKCal() {
//		return (int) Math.round((this.getProtein() + this.getCarb() + this.getFat()) / 1000);
//	}
}

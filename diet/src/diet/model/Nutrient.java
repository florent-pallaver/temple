package diet.model;

public interface Nutrient {

	double getProtein();

	double getFat();

	double getCarb();

	int getKCal();
	
//	default int getKCal() {
//		return (int) Math.round((this.getProtein() + this.getCarb() + this.getFat()) / 1000);
//	}
}

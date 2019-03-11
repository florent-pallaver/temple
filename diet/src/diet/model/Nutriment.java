package diet.model;

public interface Nutriment {

	double getProtein();

	double getFat();

	double getCarb();

	default int getKCalories() {
		return (int) Math.round((this.getProtein() + this.getCarb() + this.getFat()) / 1000);
	}
}

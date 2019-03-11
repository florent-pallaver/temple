package diet.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@MappedSuperclass
public class FoodData implements Nutriment {

	@Column(nullable = false, unique = true)
	String name;

	@Column(nullable = false)
	String brand;

	// For a portion of 1 g
	@Column(nullable = false, precision = 6, scale = 4)
	double protein;

	// For a portion of 1 g
	@Column(nullable = false, precision = 6, scale = 4)
	double fat;

	// For a portion of 1 g
	@Column(nullable = false, precision = 6, scale = 4)
	double carb;

	@Column(nullable = false, precision = 8, scale = 4)
	double kcal;

	@Column(nullable = false, precision = 6, scale = 4)
	double ig;

	@Transient
	int step;

	protected FoodData() {
		super();
	}

	public String getName() {
		return name;
	}

	@Override
	public double getProtein() {
		return protein;
	}

	@Override
	public double getFat() {
		return fat;
	}

	public String getBrand() {
		return brand;
	}

	@Override
	public double getCarb() {
		return carb;
	}

	public double getKcal() {
		return kcal;
	}

	public double getIg() {
		return ig;
	}

	public int getStep() {
		return step;
	}

	@XmlTransient
	public void set(FoodData data) {
		this.name = data.name;
		this.brand = data.brand;
		this.protein = data.protein;
		this.fat = data.fat;
		this.carb = data.carb;
		this.kcal = data.kcal;
		this.ig = data.ig;
		this.step = data.step;
	}
}

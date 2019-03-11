package diet.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@MappedSuperclass
public class Intake implements Nutriment {

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

	protected Intake() {
		super();
	}

	@Override
	public double getProtein() {
		return protein;
	}

	@Override
	public double getFat() {
		return fat;
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

	@XmlTransient
	public void set(Intake data) {
		this.protein = data.protein;
		this.fat = data.fat;
		this.carb = data.carb;
		this.kcal = data.kcal;
		this.ig = data.ig;
	}
}

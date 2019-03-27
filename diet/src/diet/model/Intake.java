package diet.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Embeddable
public class Intake implements Nutrient {

	// For a portion of 1 g
	@Column(nullable = false)
	private double protein;

	// For a portion of 1 g
	@Column(nullable = false)
	private double fat;

	// For a portion of 1 g
	@Column(nullable = false)
	private double carb;

	@Column(nullable = false)
	private int kcal;

	@Column(nullable = false)
	private int ig;

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

	@Override
	public int getKCal() {
		return kcal;
	}

	public int getIg() {
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

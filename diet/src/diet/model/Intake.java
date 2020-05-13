package diet.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Embeddable
public class Intake {

	@Column(nullable = false)
	private double protein;

	@Column(nullable = false)
	private double fat;

	@Column(nullable = false)
	private double carb;

	@Column(nullable = false)
	private double fiber;

	@XmlTransient
	@Transient
	private double alcohol;
	
	@Column(nullable = false)
	private int kcal;

	@Column(nullable = false)
	private int ig;

	protected Intake() {
		super();
	}

	public double getProtein() {
		return protein;
	}

	public double getFat() {
		return fat;
	}

	public double getCarb() {
		return carb;
	}

	// 7kcal par gramme
	public double getAlcohol() {
		return 0;
	}
	
	public int getKCal() {
		return kcal;
	}

	public double getFiber() {
		return this.fiber;
	}

	public int getIg() {
		return ig;
	}

	@XmlElement
	public IGLevel getIgLevel() {
		return IGLevel.valueOf(this.ig);
	}
	
	@XmlTransient
	public void set(Intake data) {
		this.protein = data.protein;
		this.fat = data.fat;
		this.carb = data.carb;
		this.fiber = data.fiber;
		this.kcal = data.kcal;
		this.ig = data.ig;
	}
	
	public void add(Nutrient in, int quantity) {
		final double divisor = in.getCounting().getRatio();
		final Intake intake = in.getIntake();
		this.protein += quantity * intake.protein / divisor;
		this.fat += quantity * intake.fat / divisor;
		this.carb += quantity * intake.carb / divisor;
		this.fiber += quantity * intake.fiber / divisor;
		this.computeKcal();
	}

	public Intake raw(int rawQuantity) {
		return this.cooked(rawQuantity, rawQuantity);
	}

	public Intake cooked(double rawQuantity, double cookedQuantity) {
		final double factor = rawQuantity / cookedQuantity;
		
		final Intake totalIntake = new Intake();
		totalIntake.protein += factor * this.protein;
		totalIntake.fat += factor * this.fat;
		totalIntake.carb += factor * this.carb;
		totalIntake.fiber += factor * this.fiber;
		totalIntake.computeKcal();

		return totalIntake;
	}
		
	private void computeKcal() {
		this.kcal = (int)Math.round(9 * this.fat + 4 * (this.carb + this.protein));
	}
	
}

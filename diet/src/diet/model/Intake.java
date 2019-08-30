package diet.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Embeddable
public class Intake implements Nutrient {

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

	@Override
	public double getFiber() {
		return this.fiber;
	}

	public int getIg() {
		return ig;
	}

	@Override
	public Counting getCounting() {
		return Counting.UNIT;
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
		this.protein += quantity * in.getProtein() / divisor;
		this.fat += quantity * in.getFat() / divisor;
		this.carb += quantity * in.getCarb() / divisor;
		this.fiber += quantity * in.getFiber() / divisor;
//		this.kcal += quantity * in.getKCal() / divisor;
		this.kcal = (int)Math.round(9 * this.fat + 4 * (this.carb + this.protein));
	}
}

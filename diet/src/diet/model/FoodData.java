package diet.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@MappedSuperclass
public class FoodData implements Nutrient {

	@Column(nullable = false, unique = true)
	private String name;

	@Column(nullable = false)
	private String brand;

	@Embedded
	private Intake intake = new Intake();

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = true) // FIXME should be false
	private FoodType type;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private Counting counting;
	
	protected FoodData() {
		super();
	}

	public String getName() {
		return name;
	}

	@XmlTransient
	public String getBrand() {
		return brand;
	}

	@Override
	public double getProtein() {
		return intake.getProtein();
	}

	@Override
	public double getFat() {
		return intake.getFat();
	}

	@Override
	public double getCarb() {
		return intake.getCarb();
	}

	@Override
	public double getFiber() {
		return intake.getFiber();
	}

	@Override
	public int getKCal() {
		return intake.getKCal();
	}

	public int getIg() {
		return intake.getIg();
	}

	public Counting getCounting() {
		return counting;
	}

	public void set(FoodData data) {
		this.name = data.name;
		this.brand = data.brand;
		this.intake.set(data.intake);
		this.type = data.type;
		this.counting = data.counting;
	}
}

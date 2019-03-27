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
	@Column(nullable = false)
	private FoodCounting counting;
	
//	@Transient
//	private int step;
//
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
	public int getKCal() {
		return intake.getKCal();
	}

	public int getIg() {
		return intake.getIg();
	}

	public void set(FoodData data) {
		this.name = data.name;
		this.brand = data.brand;
		this.intake.set(data.intake);
		this.counting = data.counting;
//		this.step = data.step;
	}
}

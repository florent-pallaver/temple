package diet.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlRootElement;

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
	
	@Column(nullable = false)
	private boolean archived;
	
	protected FoodData() {
		super();
	}
	
	public FoodData(String name, String brand, Intake intake, FoodType type, Counting counting) {
		super();
		this.name = name;
		this.brand = brand;
		this.intake = intake;
		this.type = type;
		this.counting = counting;
	}

	public String getName() {
		return name;
	}

	public String getBrand() {
		return brand;
	}

	@Override
	public Intake getIntake() {
		return this.intake;
	}

	@Override
	public Counting getCounting() {
		return counting;
	}

	public boolean isArchived() {
		return archived;
	}

	public void set(FoodData data) {
		this.name = data.name;
		this.brand = data.brand;
		this.intake.set(data.intake);
		this.type = data.type;
		this.counting = data.counting;
		this.archived = data.archived;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brand, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof FoodData))
			return false;
		FoodData other = (FoodData) obj;
		return Objects.equals(brand, other.brand) && Objects.equals(name, other.name);
	}
		
}

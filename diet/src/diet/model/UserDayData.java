package diet.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@MappedSuperclass
public class UserDayData extends SleepData {

	private static final int DEFAULT_METABOLIC_RATE = 1650;
	private static final int DEFAULT_METABOLIC_FACTOR = 150;

	@Column(name = "day_date", nullable = false)
	private LocalDate dayDate; 
	
	@Column(name = "user_weight", nullable = false)
	private double weight;

	@Column(name = "metabolic_rate", nullable = false)
	private int metabolicRate;

	@Column(name = "metabolic_factor", nullable = false)
	private int metabolicFactor;

	@Enumerated(EnumType.STRING)
	@Column(name="growth_mode", nullable = false)
	private GrowthMode growthMode;

	@Transient
	private String comment;

	protected UserDayData() {
		super();
	}

	public UserDayData(LocalDate dayDate, User user) {
		super();
		this.dayDate = dayDate;
		this.weight = user.getWeight();
		this.metabolicRate = DEFAULT_METABOLIC_RATE;
		this.metabolicFactor = DEFAULT_METABOLIC_FACTOR;
		this.growthMode = user.getGrowthMode();
	}

	public Intake getExpectedIntake() {
		return this.growthMode.computeIntake(this.weight);
	}

	public void set(UserDayData data) {
		// TODO
//		this.dayDate = data.dayDate;
		this.weight = data.weight;
		this.metabolicRate = data.metabolicRate;
		this.metabolicFactor = data.metabolicFactor;
		this.growthMode = data.growthMode;
//		this.comment = data.comment;
	}

}

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

	private static final int DEFAULT_WEIGHT = 75;
	private static final int DEFAULT_METABOLISM = 1650;
	private static final int DEFAULT_ACTIVITY_FACTOR = 150;
	private static final int DEFAULT_FAT_RATIO = 100;
	private static final int DEFAULT_PROTEIN_RATIO = 250;

	@Column(name = "day_date", nullable = false)
	private LocalDate dayDate; 
	
	@Column(name = "user_weight", nullable = false)
	private double weight;

	@Column(name = "metabolic_rate", nullable = false)
	private int metabolicRate;

	@Enumerated(EnumType.STRING)
	@Column(name="growth_mode", nullable = false)
	private GrowthMode growthMode;

	@Column(name = "metabolic_factor", nullable = false)
	private int activityFactor;

	@Column(name = "fat_factor", nullable = false)
    private int fatFactor;
        
	@Column(name = "protein_factor", nullable = false)
    private int proteinFactor;
        
	@Column(name="sleep_duration", nullable = false)
	private int sleepDuration;
	
	@Transient
	private int sleepQuality;

	@Column(nullable = false)
	private int fasting;
	
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private UserDayResult result;
	
	@Column(nullable = false)
	private String comments;

	protected UserDayData() {
		super();
	}

	public UserDayData(LocalDate dayDate) {
		super();
		this.dayDate = dayDate;
		this.weight = DEFAULT_WEIGHT;
		this.metabolicRate = DEFAULT_METABOLISM;
		this.activityFactor = DEFAULT_ACTIVITY_FACTOR;
		this.growthMode = GrowthMode.MAINTAIN;
        this.fatFactor = DEFAULT_FAT_RATIO;
        this.proteinFactor = DEFAULT_PROTEIN_RATIO;
        this.sleepDuration = 0;
        this.fasting = 0;
        this.result = UserDayResult.FAILURE;
        this.comments = "";
	}

	public Intake getExpectedIntake() {
		return this.growthMode.computeIntake(this.weight);
	}

	public void set(UserDayData data) {
		super.set(data);
//		this.dayDate = data.dayDate;
		this.weight = data.weight;
		this.metabolicRate = data.metabolicRate;
		this.activityFactor = data.activityFactor;
		this.growthMode = data.growthMode;
        this.fatFactor = data.fatFactor;
        this.proteinFactor = data.proteinFactor;
        this.sleepDuration = data.sleepDuration;
        this.fasting = data.fasting;
        this.result = data.result;
		this.comments = data.comments;
	}

}

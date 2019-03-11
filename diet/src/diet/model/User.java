package diet.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "user")
public class User {

	@Id
	private int id;

	@Column(nullable = false)
	private String name;

	private double weight;
	
	private Intake targetDailyIntake;
	
	private Date weighDate;
	
	private boolean growthMode;
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Intake getTargetIntake() {
		return targetDailyIntake;
	}

	public void setTargetIntake(Intake targetIntake) {
		this.targetDailyIntake = targetIntake;
	}
}

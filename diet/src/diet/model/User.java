package diet.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private double weight;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date weighDate;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private GrowthMode growthMode;
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getWeight() {
		return weight;
	}

	public GrowthMode getGrowthMode() {
		return growthMode;
	}

	public Intake getTargetIntake() {
		return this.growthMode.computeIntake(this.weight);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || (obj instanceof User && this.name.equals(((User) obj).name));
	}
}

package diet.model;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import diet.model.jaxb.MapEntry;

@XmlRootElement
@Entity
@Table(name = "user")
public class User implements DietEntity, Comparator<Food> {

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
	
	@XmlTransient
	@ElementCollection
	@CollectionTable(name = "user_food_score")
	@MapKeyJoinColumn(name = "food_id", nullable = false)
	@Column(name = "score", nullable = false)
	private Map<Food, Integer> foodScores = new HashMap<>();
	
	@Override
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
	
	public void setScore(Food food, int score) {
		if(score == 0) {
			this.foodScores.remove(food);
		} else {
			this.foodScores.put(food, score);
		}
	}

	@XmlElement
	List<MapEntry> getFoodScores() {
		return MapEntry.list(this.foodScores);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || (obj instanceof User && this.name.equals(((User) obj).name));
	}

	@Override
	public int compare(Food o1, Food o2) {
		final int score1 = foodScores.getOrDefault(o1, Integer.valueOf(0));
		final int score2 = foodScores.getOrDefault(o2, Integer.valueOf(0));
		return score2 - score1;
	}
	
}

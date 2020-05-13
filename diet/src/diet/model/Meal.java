package diet.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import diet.model.jaxb.MapEntry;

@XmlRootElement
@Entity
@Table(name = "meal")
public class Meal 
//implements Nutrient 
{

	@Id
	@Column(nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_day_id", nullable = false, updatable = false)
	private UserDay day;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "meal_time", nullable = false)
	private MealTime time;

	@XmlTransient
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "meal_food")
	@MapKeyJoinColumn(name = "food_id", nullable = false)
	@Column(name = "quantity", nullable = false)
	private Map<Food, Integer> content;

	@XmlTransient
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "meal_prepared_food")
	@MapKeyJoinColumn(name = "prepared_food_id", nullable = false)
	@Column(name = "quantity", nullable = false)
	private Map<PreparedFood, Integer> prepared;
	
	@Transient
	private String comment;

	@Transient
	private Intake intake;
	
	protected Meal() {}

	public Meal(UserDay day, MealTime time) {
		this();
		this.day = day;
		this.time = time;
		this.content = new HashMap<>();
	}

	public void setFoodQuantities(Map<Food, Integer> quantities) {
		this.content.clear();
		this.content.putAll(quantities);
		for(Entry<Food, Integer> entry: quantities.entrySet()) {
			if(entry.getValue() > 0) {
				this.content.put(entry.getKey(), entry.getValue());
			}
		}
		this.computeIntake();
	}

	@PostLoad
	private void computeIntake() {
		this.intake = new Intake();
		for(Entry<Food, Integer> entry: content.entrySet()) {
			this.intake.add(entry.getKey(), entry.getValue());
		}
	}
	
	@XmlElement
	List<MapEntry> getContent() {
		return MapEntry.list(this.content);
	}
	
}

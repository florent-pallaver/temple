package diet.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity
@Table(name = "user_day", 
		uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "day_date"})
		)
public class UserDay extends UserDayData implements Owned {

	@Id
	@Column(nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@XmlTransient
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private User user;

	@Column(name="sleep_duration", nullable = false)
	private int sleepDuration;
	
	@Transient
	private int sleepQuality;

	@XmlTransient
	@OneToMany(mappedBy = "day", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	@OrderBy("time asc")
	private List<Meal> meals;
	
	@Transient
	private String comment;

	protected UserDay() {
		super();
	}

	public UserDay(LocalDate dayDate, User user) {
		super(dayDate, user);
		this.user = user;
		this.meals = new ArrayList<>();
		for(final MealTime time: MealTime.values()) {
			this.meals.add(new Meal(this, time));
		}
	}

	@Override
	public User getOwner() {
		return this.user;
	}

	public List<Meal> getMeals() {
		return meals;
	}

	public Meal getMeal(MealTime time) {
		return this.meals.get(time.ordinal());
	}
}

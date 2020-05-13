package diet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity
@Table(name = "prepared_food")
public class PreparedFood extends PreparedFoodData implements Nutrient {
	
	@Id
	@Column(nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@XmlTransient
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Food food;
	
	protected PreparedFood() {}
	
	public PreparedFood(Food food, PreparedFoodData data) {
		super(data);
		this.food = food;
	}
	
	@XmlElement
	public Intake getIntake() {
		return food.getIntake().cooked(rawQuantity, cookedQuantity);
	}
	
	@Override
	public Counting getCounting() {
		return this.food.getCounting();
	}
}

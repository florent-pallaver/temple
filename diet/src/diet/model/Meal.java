package diet.model;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "meal")
public class Meal implements Nutriment {

	@Id
	private int id;

	@ManyToOne(optional = false)
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "meal_eater"))
	private User eater;

	@Column(nullable = false)
	private Date eatDate;

	@ElementCollection
	@CollectionTable(name = "meal_content")
	@MapKeyJoinColumn(name = "food_id", nullable = false)
	@Column(name = "quantity", nullable = false)
	private Map<Food, Integer> content;

	@Transient
	private String comment;

	public void setFoodQuantity(Food food, int quantity) {
		if(quantity <= 0) {
			this.content.remove(food);
		} else {
			this.content.put(food, quantity);
		}
	}

	@Override
	public double getProtein() {
		return sum(Food::getProtein);
	}

	@Override
	public double getFat() {
		return sum(Food::getFat);
	}

	@Override
	public double getCarb() {
		return sum(Food::getCarb);
	}

	private double sum(Function<Food, Double> getter) {
		return content.keySet().stream()
				.mapToDouble(food -> getter.apply(food) * content.get(food))
				.sum();
	}
}

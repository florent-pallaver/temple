package diet.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "food")
public class Food extends FoodData {

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	private Date creationDate;

	protected Food() {
		super();
	}

	public Food(FoodData data) {
		super();
		this.creationDate = new Date();
		this.set(data);
	}

	public int getId() {
		return id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	@Override
	public String toString() {
		return "Food [id=" + id + ", name=" + name + ", brand=" + brand + ", protein=" + protein + ", fat=" + fat
				+ ", carb=" + carb + ", kcal=" + kcal + ", ig=" + ig + ", step=" + step + "]";
	}

}

package diet.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "food")
public class Food extends FoodData implements DietEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", nullable = false, updatable = false)
	private Date creationDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_update", nullable = false)
	private Date lastUpdate;
	
	protected Food() {
		super();
	}

	public Food(FoodData data) {
		super();
		this.set(data);
		this.creationDate = this.lastUpdate;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void set(FoodData data) {
		super.set(data);
		this.lastUpdate = new Date();
	}
}

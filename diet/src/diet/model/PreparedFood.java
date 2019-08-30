package diet.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity
@Table(name = "prepared_food")
public class PreparedFood implements Owned, Nutrient {
	
	@Id
	@Column(nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String name;

	@XmlTransient
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private User owner;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", nullable = false, updatable = false)
	private Date creationDate;
	
	@XmlTransient
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Food food;
	
	@Column(name="raw_quantity", nullable = false)
	private int rawQuantity;
	
	@Column(name="cooked_quantity", nullable = false)
	private int cookedQuantity;

	@Column(name="used_quantity", nullable = false)
	private int usedQuantity;
	
	@Override
	public User getOwner() {
		return owner;
	}
	
	@Override
	public double getProtein() {
		return food.getProtein() * rawQuantity / cookedQuantity;
	}

	@Override
	public double getFat() {
		return food.getFat() * rawQuantity / cookedQuantity;
	}

	@Override
	public double getCarb() {
		return food.getCarb() * rawQuantity / cookedQuantity;
	}

	@Override
	public double getFiber() {
		return food.getFiber() * rawQuantity / cookedQuantity;
	}

	@Override
	public int getKCal() {
		return food.getKCal() * rawQuantity / cookedQuantity;
	}

	@Override
	public Counting getCounting() {
		return this.food.getCounting();
	}
}

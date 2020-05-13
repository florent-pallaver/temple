package diet.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
//@Entity
//@Table(name = "dish")
public class Dish implements Owned, Nutrient {
	
	@Id
	@Column(nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", nullable = false, updatable = false)
	private Date creationDate;

	@Column(nullable = false)
	private String name;
	
	@XmlTransient
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private User owner;
	
	@XmlTransient
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private List<PreparedFood> food;
	
	@Column(name = "used_quantity", nullable = false)
	private int usedQuantity;
	
	protected Dish() {}
	
	public Dish(User owner, String name) {
		this.creationDate = new Date();
		this.owner = owner;
		this.name = name;
		this.usedQuantity = 0;
	}

	@Override
	public User getOwner() {
		return owner;
	}

	@Override
	public Intake getIntake() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Counting getCounting() {
		return Counting.GRAM;
	}
}

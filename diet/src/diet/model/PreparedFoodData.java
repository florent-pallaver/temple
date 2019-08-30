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
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@MappedSuperclass
public class PreparedFoodData implements Nutrient {
	
	@Column(nullable = false)
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", nullable = false, updatable = false)
	private Date creationDate;
	
	@Column(nullable = false)
	private int rawQuantity;
	
	@Column(nullable = false)
	private int cookedQuantity;

	@Column(nullable = false)
	private int usedQuantity;

	@Override
	public double getProtein() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getFat() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getCarb() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getFiber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getKCal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Counting getCounting() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

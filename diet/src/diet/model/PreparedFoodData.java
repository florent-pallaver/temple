package diet.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@MappedSuperclass
public class PreparedFoodData {
	
	@XmlElement(name = "raw")
	@Column(name = "raw_quantity", nullable = false)
	int rawQuantity;
	
	@XmlElement(name = "cooked")
	@Column(name = "cooked_quantity", nullable = false)
	int cookedQuantity;

	protected PreparedFoodData() {
		super();
	}

	protected PreparedFoodData(PreparedFoodData data) {
		super();
		this.rawQuantity = data.rawQuantity;
		this.cookedQuantity = data.cookedQuantity;
	}

}

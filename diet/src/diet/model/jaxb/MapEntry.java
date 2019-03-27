package diet.model.jaxb;

import javax.xml.bind.annotation.XmlElement;

public class MapEntry {

	@XmlElement
	public final String key;
	
	@XmlElement
	public final Integer value;
	
	protected MapEntry() {
		this((String)null, null);
	}

	public MapEntry(String key, Integer value) {
		super();
		this.key = key;
		this.value = value;
	}

	public MapEntry(Integer key, Integer value) {
		super();
		this.key = key.toString();
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public Integer getValue() {
		return value;
	}

	
	
}

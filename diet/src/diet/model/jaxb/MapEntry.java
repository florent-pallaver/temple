package diet.model.jaxb;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;

import diet.model.DietEntity;

public class MapEntry {

	@XmlElement
	public final String key;
	
	@XmlElement
	public final Integer value;
	
	protected MapEntry() {
		this((String)null, null);
	}

	public MapEntry(Entry<? extends DietEntity, Integer> entry) {
		this(entry.getKey().getId(), entry.getValue());
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

	public static List<MapEntry> list(Map<? extends DietEntity, Integer> entries) {
		return entries.entrySet().stream()
			.map(MapEntry::new)
			.collect(Collectors.toList());
	}
	
}

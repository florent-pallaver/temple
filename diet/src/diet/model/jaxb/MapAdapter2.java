package diet.model.jaxb;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MapAdapter2 extends XmlAdapter<List<MapEntry>, Map<Integer, Integer>> {

	@Override
	public List<MapEntry> marshal(Map<Integer, Integer> map) throws Exception {
		return map.entrySet().stream()
			.map(entry -> new MapEntry(entry.getKey(), entry.getValue()))
			.collect(Collectors.toList());
	}

	@Override
	public Map<Integer, Integer> unmarshal(List<MapEntry> entries) throws Exception {
		return entries.stream()
			.collect(Collectors.toMap(entry -> Integer.valueOf(entry.key), entry -> entry.value));
	}


}

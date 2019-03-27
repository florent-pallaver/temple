package diet.model.jaxb;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MapAdapter extends XmlAdapter<MapEntry[], Map<String, Integer>> {

	@Override
	public MapEntry[] marshal(Map<String, Integer> map) throws Exception {
		MapEntry[] entries = new MapEntry[map.size()];
		int i = 0;
		for(Entry<String, Integer> entry: map.entrySet()) {
			entries[i++] = new MapEntry(entry.getKey(), entry.getValue());
		}
		return entries;
	}

	@Override
	public Map<String, Integer> unmarshal(MapEntry[] entries) throws Exception {
		final Map<String, Integer> map = new HashMap<>();
		Stream.of(entries)
			.forEach(entry -> map.put(entry.key, entry.value));
		return map ;
	}


}

package com.temple.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Base contract for anything that has a name.
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public interface Nameable {

	String getName();

	static Set<String> toNameSet(Collection<? extends Nameable> named) {
		return named.stream()
				.map(Nameable::getName)
				.collect(Collectors.toSet());
	}
	
	static <NAMED extends Nameable> Map<String, NAMED> toNameMap(Set<NAMED> named) {
		return named.stream()
				.collect(Collectors.toMap(Nameable::getName, Function.identity()));
	}
	
}

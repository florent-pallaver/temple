package com.temple.model.filter;

import com.temple.model.TempleEntity;

@FunctionalInterface
public interface LongAggregator<T extends TempleEntity> extends Aggregator<T, Long> {
	
	@Override
	default Class<Long> getAggregateClass() {
		return Long.class;
	}
	
	static <E extends TempleEntity> LongAggregator<E> count() {
		return (cb, root) -> cb.count(root);
	}
}
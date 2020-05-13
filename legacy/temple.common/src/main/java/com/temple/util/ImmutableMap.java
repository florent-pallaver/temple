package com.temple.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Immutable implementation of {@link Map}.
 * <p>
 * It does not allow <code>null</code> values or <code>null</code> keys.
 * 
 * @author cr9z1k
 * @version 1.0
 * @param <K> the type of keys maintained by this map.
 * @param <V> the type of mapped values.
 */
public class ImmutableMap<K, V> implements Map<K, V>, Serializable {

	private static final long serialVersionUID = 1L;

	private transient Set<K> keySet;

	private transient Set<V> valueSet;

	private transient Set<Entry<K, V>> entrySet;

	final K[] keys;

	final V[] values;

	/**
	 * Constructor.
	 * <p>
	 * The keys and the values will be associated as they are ordered in their respective array.
	 * 
	 * @param keys - the keys of this Map.
	 * @param values - the values of this Map.
	 * @throws NullPointerException if any of the given parameter is <code>null</code>, or contains <code>null</code>.
	 * @throws IllegalArgumentException if the given parameters don't have the same size.
	 */
	public ImmutableMap(K[] keys, V[] values) {
		if (TempleUtil.contains(keys, null) || TempleUtil.contains(values, null)) {
			throw new NullPointerException();
		}
		if (keys.length != values.length) {
			throw new IllegalArgumentException();
		}
		this.keys = keys;
		this.values = values;
	}

	@Override
	public final V get(Object key) {
		if (key == null) {
			throw new NullPointerException("null keys are not allowed.");
		}
		final int i = TempleUtil.linearSearch(this.keys, key);
		return i == TempleUtil.VALUE_NOT_FOUND ? null : this.values[i];
	}

	@Override
	public final Set<K> keySet() {
		if (this.keySet == null) {
			this.keySet = new ImmutableFixedSet<>(this.keys);
		}
		return this.keySet;
	}

	@Override
	public final Collection<V> values() {
		if (this.valueSet == null) {
			this.valueSet = new ImmutableFixedSet<>(this.values);
		}
		return this.valueSet;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final Set<java.util.Map.Entry<K, V>> entrySet() {
		if (this.entrySet == null) {
			Entry<K, V>[] es = (Entry<K, V>[]) new Entry<?, ?>[this.keys.length];
			for (int i = es.length; i-- > 0;) {
				es[i] = new PrivateEntry(i);
			}
			this.entrySet = new ImmutableFixedSet<>(es);
		}
		return this.entrySet;
	}

	@Override
	public final int size() {
		return this.keys.length;
	}

	@Override
	public final boolean isEmpty() {
		return this.keys.length == 0;
	}

	@Override
	public final boolean containsKey(Object key) {
		return TempleUtil.contains(this.keys, key);
	}

	@Override
	public final boolean containsValue(Object value) {
		return TempleUtil.contains(this.values, value);
	}

	@Override
	public final V put(K key, V value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void putAll(Map<? extends K, ? extends V> m) {
		throw new UnsupportedOperationException();
	}

	@Override
	public final V remove(Object key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void clear() {
		throw new UnsupportedOperationException();
	}

	private final class PrivateEntry implements Entry<K, V> {

		private final int index;

		PrivateEntry(int index) {
			super();
			this.index = index;
		}

		@Override
		public final K getKey() {
			return ImmutableMap.this.keys[this.index];
		}

		@Override
		public final V getValue() {
			return ImmutableMap.this.values[this.index];
		}

		@Override
		public final V setValue(V value) {
			throw new UnsupportedOperationException();
		}
	}
}

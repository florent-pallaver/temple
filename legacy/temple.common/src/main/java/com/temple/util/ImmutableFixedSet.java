package com.temple.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An immutable set of elements.
 * <p>
 * The {@link #iterator()} of this Set will always iterate in the same order.
 * 
 * @author cr9z1k
 * @version 1.0
 * @param <T> - the type of elements this set will contain.
 */
public final class ImmutableFixedSet<T> extends AbstractImmutableArraySet<T> {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param elements - the elements of this ImmutableSet.
	 * @throws NullPointerException if the given elements are <code>null</code>.
	 */
	public ImmutableFixedSet(T[] elements) {
		super(elements);
	}

	@Override
	public Iterator<T> iterator() {
		return new PrivateIterator<>(this.toArray(this.emptyArray()));
	}

	private static final class PrivateIterator<T> implements Iterator<T> {

		private final T[] objects;

		private int i = 0;

		PrivateIterator(T[] objects) {
			super();
			this.objects = objects;
		}

		@Override
		public final boolean hasNext() {
			return this.objects.length > this.i;
		}

		@Override
		public final T next() {
			if (this.i >= this.objects.length) {
				throw new NoSuchElementException();
			}
			return this.objects[this.i++];
		}

		@Override
		public final void remove() {
			throw new UnsupportedOperationException();
		}
	}
}

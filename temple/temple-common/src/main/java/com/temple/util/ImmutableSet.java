package com.temple.util;

import java.util.Iterator;

/**
 * An immutable set of elements.
 * 
 * @author crezik
 * @version 1.0
 * @param <T> - the type of elements this set will contain.
 */
public final class ImmutableSet<T> extends AbstractImmutableArraySet<T> {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param elements - the elements of this ImmutableSet.
	 * @throws NullPointerException if the given elements are <code>null</code>.
	 */
	public ImmutableSet(T[] elements) {
		super(elements);
	}

	@Override
	public final Iterator<T> iterator() {
		return new PrivateIterator<>(this.toArray(this.emptyArray()));
	}

	private static final class PrivateIterator<E> implements Iterator<E> {

		private final int[] indexes;

		private final E[] elements;

		private int current;

		/**
		 * Constructor
		 */
		PrivateIterator(E[] elements) {
			super();
			this.elements = elements;
			this.indexes = new int[elements.length];
			this.current = 0;
			for (int i = elements.length; i-- > 0; this.indexes[i] = i) {
				;
			}
			TempleUtil.shuffle(this.indexes);
		}

		@Override
		public boolean hasNext() {
			return this.current < this.elements.length;
		}

		@Override
		public E next() {
			return this.elements[this.indexes[this.current++]];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}

package com.temple.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

abstract class AbstractImmutableArraySet<O> implements Set<O>, Serializable {

	private static final long serialVersionUID = 1L;

	private final Object[] objects;

	AbstractImmutableArraySet(O[] objects) {
		if (objects == null) {
			throw new NullPointerException();
		}
		int k = 0;
		Object[] a = new Object[objects.length];
		for (int i = 0, l = objects.length; i < l; i++) {
			final Object o = objects[i];
			if (!TempleUtil.contains(a, o)) {
				a[k++] = o;
			}
		}
		this.objects = new Object[k];
		System.arraycopy(a, 0, this.objects, 0, k);
	}

	@Override
	public final boolean add(O e) {
		throw new UnsupportedOperationException();
	}

	@Override
	public final boolean addAll(Collection<? extends O> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public final boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public final boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public final void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public final boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public final int size() {
		return this.objects.length;
	}

	@Override
	public final boolean isEmpty() {
		return this.objects.length == 0;
	}

	@Override
	public final boolean contains(Object o) {
		return TempleUtil.contains(this.objects, o);
	}

	@Override
	public final boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (!this.contains(o)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public final Object[] toArray() {
		return Arrays.copyOf(this.objects, this.objects.length);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final <T> T[] toArray(T[] a) {
		if (a == null) {
			throw new NullPointerException();
		}
		if (!a.getClass().getComponentType().isAssignableFrom(this.objects.getClass().getComponentType())) {
			throw new ArrayStoreException();
		}
		final int ol = this.objects.length;
		final int al = a.length;
		final T[] r;
		if (ol < al) {
			Arrays.fill(a, null);
		}
		if (ol > al) {
			r = Arrays.copyOf(this.objects, ol, (Class<? extends T[]>) a.getClass());
		} else {
			r = a;
			System.arraycopy(this.objects, 0, a, 0, ol);
		}
		return r;
	}

	@SuppressWarnings("unchecked")
	final O[] emptyArray() {
		return (O[]) new Object[0];
	}
}

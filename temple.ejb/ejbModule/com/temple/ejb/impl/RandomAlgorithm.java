package com.temple.ejb.impl;

import java.util.Collection;
import java.util.Map;

import com.temple.util.TempleUtil;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 */
public interface RandomAlgorithm {

	/**
	 * TODOC
	 * 
	 * @return
	 */
	Object findRandom();

	/**
	 * TODOC
	 * 
	 * @author Florent Pallaver
	 */
	public abstract static class Abstract implements RandomAlgorithm {

		protected Collection<?> objects;

		/**
		 * @param objects
		 */
		protected Abstract(Map<?, ?> objects) {
			super();
			this.objects = objects.values();
		}
	}

	/**
	 * TODOC
	 * 
	 * @author Florent Pallaver
	 */
	public static final class Simple extends Abstract {

		public Simple(Map<?, ?> objects) {
			super(objects);
		}

		@Override
		public Object findRandom() {
			int r = TempleUtil.random(0, this.objects.size());
			for (final Object e : this.objects) {
				if (r-- == 0) {
					return e;
				}
			}
			// should never happen
			throw new RuntimeException("Unable to find a random element!");
		}
	}

	/**
	 * TODOC
	 * 
	 * @author Florent Pallaver
	 */
	public static final class ArrayBased extends Abstract {

		private int lastSize = 0;

		private Object[] objectsArray = {};

		public ArrayBased(Map<?, ?> objects) {
			super(objects);
		}

		@Override
		public Object findRandom() {
			this.check();
			return this.objectsArray[TempleUtil.random(0, this.lastSize)];
		}

		private void check() {
			final int size = this.objects.size();
			if (size != this.lastSize) {
				this.objectsArray = this.objects.toArray();
				this.lastSize = size;
			}
		}
	}
}

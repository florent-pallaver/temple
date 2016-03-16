package com.temple.model.filter;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.temple.model.TempleEntity;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 * @param <E>
 *            the class to which this {@link FilterSelection} may apply to
 */
public interface FilterSelection<R extends Serializable, E extends TempleEntity> extends Serializable {

	/**
	 * @return TODOC
	 */
	Class<R> getSelectedClass();

	/**
	 * @return the {@link SelectionCriteria} list.
	 */
	List<SelectionCriteria<E>> getSelectedFields();

	/**
	 * Tells if an entity class is compatible with this {@link FilterSelection}.
	 *
	 * @param c
	 *            an entity class
	 * @return <code>true</code> if this {@link FilterSelection} can apply to
	 *         the given entity class, <code>false</code> otherwise.
	 */
	boolean accepts(Class<? extends TempleEntity> c);

	class Self<E extends TempleEntity> implements FilterSelection<E, E> {

		private static final long serialVersionUID = 1L;

		private final Class<E> clazz;

		private Self(Class<E> clazz) {
			super();
			this.clazz = clazz;
		}

		@Override
		public Class<E> getSelectedClass() {
			return this.clazz;
		}

		@Override
		public List<SelectionCriteria<E>> getSelectedFields() {
			return Collections.emptyList();
		}

		@Override
		public boolean accepts(Class<? extends TempleEntity> c) {
			return true;
		}

		public static final <TE extends TempleEntity> Self<TE> create(Class<TE> entityClass) {
			return new Self<>(entityClass);
		}

		private static final class Cache {

			// private static final Map<String, V>

		}

	}

}

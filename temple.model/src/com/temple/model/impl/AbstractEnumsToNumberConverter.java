package com.temple.model.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeConverter;

/**
 * TODOC <br>
 * Note: because of <a>https://bugs.eclipse.org/bugs/show_bug.cgi?id=414679</a>,
 * concrete subclasses must explicitly implement {@link AttributeConverter}.
 *
 * @author Florent Pallaver
 * @version 1.0
 * @param <E>
 * @param <T>
 */
public abstract class AbstractEnumsToNumberConverter<E extends Enum<E>, T extends Number> implements AttributeConverter<Set<E>, T> {

	private final E[] all;

	/**
	 * Constructor.
	 *
	 * @param all
	 *            TODOC
	 */
	protected AbstractEnumsToNumberConverter(E[] all) {
		this.all = all;
	}

	/**
	 * TODOC
	 *
	 * @param mask
	 * @return
	 */
	protected abstract T castMask(Long mask);

	/**
	 * TODOC
	 *
	 * @param dbValue
	 * @return
	 */
	protected long toMask(T dbValue) {
		return dbValue.longValue();
	}

	@Override
	public final T convertToDatabaseColumn(Set<E> attribute) {
		final Long value;
		if (attribute != null) {
			long m = 0;
			for (final E e : attribute) {
				m |= 1 << e.ordinal();
			}
			value = m;
		} else {
			value = null;
		}
		return this.castMask(value);
	}

	@Override
	public final Set<E> convertToEntityAttribute(T dbData) {
		final Set<E> attribute = new HashSet<>();
		if (dbData != null) {
			final long mask = this.toMask(dbData);
			for (int i = 0, l = this.all.length; i < l; i++) {
				if ((mask >> i & 1) == 1) {
					attribute.add(this.all[i]);
				}
			}
		}
		return attribute;
	}
}

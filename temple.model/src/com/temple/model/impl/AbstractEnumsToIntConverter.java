package com.temple.model.impl;

import java.util.Arrays;

import javax.persistence.AttributeConverter;

/**
 * TODOC <br>
 * Note: because of <a>https://bugs.eclipse.org/bugs/show_bug.cgi?id=414679</a>, concrete subclasses must explicitly
 * implement {@link AttributeConverter}.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class AbstractEnumsToIntConverter<E extends Enum<E>> implements AttributeConverter<E[], Long> {

	private final E[] all;

	/**
	 * Constructor.
	 */
	protected AbstractEnumsToIntConverter(E[] all) {
		this.all = all;
	}

	@Override
	public Long convertToDatabaseColumn(E[] attribute) {
		final Long value;
		if (attribute != null) {
			long m = 0;
			for (int i = attribute.length; i-- > 0;) {
				m |= 1 << attribute[i].ordinal();
			}
			value = Long.valueOf(m);
		} else {
			value = null;
		}
		return value;
	}

	@Override
	public E[] convertToEntityAttribute(Long dbData) {
		E[] e;
		if (dbData != null) {
			e = this.all.clone();
			final long mask = dbData.longValue();
			int j = 0;
			for (int i = 0, l = this.all.length; i < l; i++) {
				if ((mask >> i & 1) == 1) {
					e[j++] = this.all[i];
				}
			}
			e = Arrays.copyOf(e, j);
		} else {
			e = Arrays.copyOf(this.all, 0);
		}
		return e;
	}
}

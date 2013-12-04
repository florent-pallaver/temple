package com.temple.model.impl;

import java.util.ArrayList;
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
public abstract class AbstractEnumsConverter<E extends Enum<E>> implements AttributeConverter<E[], Long> {

	private final E[] all;

	/**
	 * Constructor.
	 */
	protected AbstractEnumsConverter(E[] all) {
		this.all = all;
	}

	@Override
	public Long convertToDatabaseColumn(E[] attribute) {
		final Long l;
		if (attribute != null) {
			long m = 0;
			for (int i = attribute.length; i-- > 0;) {
				m |= 1 << attribute[i].ordinal();
			}
			l = Long.valueOf(m);
		} else {
			l = null;
		}
		return l;
	}

	@Override
	public E[] convertToEntityAttribute(Long dbData) {
		final E[] e;
		if (dbData != null) {
			final ArrayList<E> ls = new ArrayList<>(this.all.length);
			final long mask = dbData.longValue();
			for (int i = 0, l = this.all.length; i < l; i++) {
				if ((mask >> i & 1) == 1) {
					ls.add(this.all[i]);
				}
			}
			e = ls.toArray(Arrays.copyOf(this.all, ls.size()));
		} else {
			e = null;
		}
		return e;
	}
}

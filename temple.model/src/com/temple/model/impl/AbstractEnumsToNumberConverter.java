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
 * @param <E>
 * @param <T>
 */
public abstract class AbstractEnumsToNumberConverter<E extends Enum<E>, T extends Number> implements AttributeConverter<E[], T> {

	private final E[] all;

	/**
	 * Constructor.
	 * @param all TODOC
	 */
	protected AbstractEnumsToNumberConverter(E[] all) {
		this.all = all;
	}

	/**
	 * TODOC
	 * @param mask
	 * @return 
	 */
	protected abstract T castMask(Long mask) ;
	
	/**
	 * TODOC
	 * @param dbValue
	 * @return 
	 */
	protected long toMask(T dbValue)  {
		return dbValue.longValue() ;
	}
	
	@Override
	public final T convertToDatabaseColumn(E[] attribute) {
		final Long value;
		if (attribute != null) {
			long m = 0;
			for (int i = attribute.length; i-- > 0;) {
				m |= 1 << attribute[i].ordinal();
			}
			value = m;
		} else {
			value = null;
		}
		return this.castMask(value);
	}

	@Override
	public final E[] convertToEntityAttribute(T dbData) {
		E[] e;
		if (dbData != null) {
			e = this.all.clone();
			final long mask = this.toMask(dbData) ;
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

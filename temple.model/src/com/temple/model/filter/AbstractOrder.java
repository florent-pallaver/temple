package com.temple.model.filter;

import com.temple.model.TempleEntity;
import com.temple.model.filter.FilterOrder;
import com.temple.model.filter.OrderCriteria;
import com.temple.util.TempleUtil;
import com.temple.util.ToString;
import java.io.Serializable;
import javax.persistence.metamodel.SingularAttribute;

/**
 * TODOC
 * Note : implementation takes into account the fact {@link SingularAttribute} is not {@link Serializable}.
 *
 * @author Florent Pallaver
 * @version 1.0
 * @param <E>
 */
public abstract class AbstractOrder<E extends TempleEntity> implements FilterOrder<E> {

	private static final long serialVersionUID = 1L;

	private transient OrderCriteria<E> oc;

	@ToString
	private final boolean asc;

	private final Class<? super E> accepted ;
	
	protected AbstractOrder(boolean asc, Class<? super E> accepted) {
		this.asc = asc;
		this.accepted = accepted ;
	}

	@Override
	public final boolean isAsc() {
		return this.asc ;
	}
	
	@Override
	public final OrderCriteria<E> getCriteria() {
		if (this.oc == null) {
			this.oc = new OrderCriteria<>(this.getField(), this.asc) ;
		}
		return this.oc;
	}

	protected abstract SingularAttribute<? super E, ?> getField();

	@Override
	public final boolean accepts(Class<? extends TempleEntity> c) {
		return this.accepted.isAssignableFrom(c) ;
	}

	@Override
	public String toString() {
		return TempleUtil.toString(this) ;
	}
	
}

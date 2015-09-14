package com.temple.model.geo.geonames.filter;

import com.temple.model.filter.AbstractOrder;
import com.temple.model.filter.FilterOrder;
import com.temple.model.geo.geonames.AbstractCountryArea;
import com.temple.model.geo.geonames.AbstractCountryArea_;
import javax.persistence.metamodel.SingularAttribute;

/**
 * TODOC
 * @author flominou
 */
public final class NameOrder extends AbstractOrder<AbstractCountryArea>{
	
	private static final long serialVersionUID = 1L;

	public static final FilterOrder<AbstractCountryArea> ASC = new NameOrder(true);

	public static final FilterOrder<AbstractCountryArea> DESC = new NameOrder(false);

	private NameOrder(boolean asc) {
		super(asc, AbstractCountryArea.class);
	}
	
	@Override
	protected SingularAttribute<? super AbstractCountryArea, ?> getField() {
		return AbstractCountryArea_.name ;
	}
	
	
}

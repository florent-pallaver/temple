package com.temple.geo.model;

import com.temple.util.geo.HumanSettlement;

/**
 * Util interface extending {@link HumanSettlement} and {@link GeoEntity}.
 *
 * @author flominou
 * @version 1.0
 */
public interface HumanSettlementEntity extends HumanSettlement, GeoEntity {

	@Override
	Integer getId() ;

}

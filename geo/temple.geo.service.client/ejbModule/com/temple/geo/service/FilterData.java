/**
 *
 */
package com.temple.geo.service;

import com.temple.util.geo.Country;
import com.temple.util.json.AbstractJsonable;
import com.temple.util.json.JsonField;

/**
 * @author flominou
 *
 */
public final class FilterData extends AbstractJsonable {

	@JsonField
	private Country country;

	@JsonField
	private int parentId;

	@JsonField
	private int page;

	@JsonField
	private int count;

	@JsonField
	private String like;

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return this.country;
	}

	/**
	 * @return the parentId
	 */
	public int getParentId() {
		return this.parentId;
	}

	/**
	 * @return the start
	 */
	public int getPage() {
		return this.page;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return this.count;
	}

	/**
	 * @return the like
	 */
	public String getLike() {
		return this.like;
	}

}

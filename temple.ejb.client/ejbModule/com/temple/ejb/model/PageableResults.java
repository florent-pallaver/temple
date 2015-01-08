package com.temple.ejb.model;

import java.util.List;

import com.temple.model.TempleEntity;

/**
 * TODOC
 *
 * @author Florent Pallaver
 */
public class PageableResults<E extends TempleEntity> implements PartialResults<E> {

	private static final long serialVersionUID = 1L;

	private final List<E> entities;

	private final int totalCount;

	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param entities
	 * @param filter
	 * @param totalCount
	 * @param perPageCount
	 */
	public PageableResults(List<E> entities, int totalCount) {
		super();
		this.entities = entities;
		this.totalCount = totalCount;
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param entities
	 * @param totalCount
	 */
	public PageableResults(List<E> entities, long totalCount) {
		super();
		this.entities = entities;
		this.totalCount = (int) totalCount;
	}

	@Override
	public List<E> getAll() {
		return this.entities;
	}

	@Override
	public final int getTotalCount() {
		return this.totalCount;
	}
}

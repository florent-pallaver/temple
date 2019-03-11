package com.temple.model.impl;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.temple.TempleObject;
import com.temple.model.TempleEntity;
import com.temple.util.ToString;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@MappedSuperclass
public abstract class AbstractEntity extends TempleObject implements TempleEntity {

	private static final long serialVersionUID = 1L;

	@ToString
	@Column(nullable = false, updatable = false)
	private Timestamp creationTS;

	/**
	 * Constructor.
	 */
	protected AbstractEntity() {}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param creationTS
	 */
	protected AbstractEntity(Timestamp creationTS) {
		super();
		this.creationTS = creationTS;
	}

	@Override
	public Timestamp getCreationTS() {
		return this.creationTS;
	}
}

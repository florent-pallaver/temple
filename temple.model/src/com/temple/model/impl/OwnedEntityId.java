package com.temple.model.impl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.temple.util.TempleUtil;
import com.temple.util.ToString;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 */
@Embeddable
public class OwnedEntityId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ToString
	@Column(nullable = false, updatable = false)
	private int ownerId;

	@ToString
	@Column(nullable = false, updatable = false)
	private int selfId;

	/**
	 * Constructor.
	 */
	public OwnedEntityId() {
		super();
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param ownerId
	 * @param selfId
	 */
	public OwnedEntityId(int ownerId, int selfId) {
		super();
		this.ownerId = ownerId;
		this.selfId = selfId;
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param ownerId
	 * @param selfId
	 */
	public OwnedEntityId(Integer ownerId, Integer selfId) {
		this(ownerId.intValue(), selfId.intValue());
	}

	/**
	 * @return the ownerId
	 */
	public int getOwnerId() {
		return this.ownerId;
	}

	/**
	 * @return the selfId
	 */
	public int getSelfId() {
		return this.selfId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.ownerId;
		result = prime * result + this.selfId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		final boolean e;
		if (this != obj && obj instanceof OwnedEntityId) {
			final OwnedEntityId other = (OwnedEntityId) obj;
			e = this.ownerId == other.ownerId && this.selfId == other.selfId;
		} else {
			e = this == obj;
		}
		return e;
	}

	@Override
	public String toString() {
		return TempleUtil.toString(this);
	}
}

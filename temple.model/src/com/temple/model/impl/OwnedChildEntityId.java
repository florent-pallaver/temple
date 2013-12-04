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
 * @version 1.0
 */
@Embeddable
public class OwnedChildEntityId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ToString
	@Column(nullable = false, updatable = false)
	private int ownerId;

	@ToString
	@Column(nullable = false, updatable = false)
	private int parentId;

	@ToString
	@Column(nullable = false, updatable = false)
	private int selfId;

	/**
	 * Constructor.
	 */
	public OwnedChildEntityId() {
		super();
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param ownerId
	 * @param parentId
	 * @param selfId
	 */
	public OwnedChildEntityId(int ownerId, int parentId, int selfId) {
		this.ownerId = ownerId;
		this.selfId = selfId;
		this.parentId = parentId;
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

	/**
	 * @return the parentId
	 */
	public int getParentId() {
		return this.parentId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.ownerId;
		result = prime * result + this.parentId;
		result = prime * result + this.selfId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof OwnedChildEntityId)) {
			return false;
		}
		final OwnedChildEntityId other = (OwnedChildEntityId) obj;
		return this.ownerId == other.ownerId && this.parentId == other.parentId && this.selfId == other.selfId;
	}

	@Override
	public String toString() {
		return TempleUtil.toString(this);
	}
}

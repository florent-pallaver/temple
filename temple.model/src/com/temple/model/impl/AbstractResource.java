package com.temple.model.impl;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.temple.model.Group;
import com.temple.model.ModelUtil;
import com.temple.model.Resource;
import com.temple.model.User;
import com.temple.model.impl.access.DefaultMod;
import com.temple.util.ToString;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 * @param <R>
 */
@MappedSuperclass
public abstract class AbstractResource<I extends Serializable> extends AbstractEntity implements Resource<DefaultMod> {

	private static final long serialVersionUID = 1L;

	@ToString
	@Embedded
	private DefaultMod accessRights;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = AbstractUser.class)
	@JoinColumn(name = "ownerId", nullable = false, updatable = false)
	private User<DefaultMod, ?> owner;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = AbstractGroup.class)
	@JoinColumn(name = "primaryGroupId", nullable = false, updatable = false)
	private Group<DefaultMod> primaryGroup;

	/**
	 * Constructor.
	 */
	protected AbstractResource() {
		super();
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param accessRights
	 * @param owner
	 * @param primaryGroup
	 */
	protected AbstractResource(DefaultMod accessRights, User<DefaultMod, ?> owner, Group<DefaultMod> primaryGroup) {
		super(ModelUtil.currentTimestamp());
		this.accessRights = accessRights;
		this.owner = owner;
		this.primaryGroup = primaryGroup;
	}

	@Override
	public abstract I getId();

	/**
	 * Sets the id. <br>
	 * Should be used only if the id isn't automatically generated.
	 * 
	 * @param id the id to set
	 */
	protected abstract void setId(I id);

	@Override
	public DefaultMod getAccessRights() {
		return this.accessRights;
	}

	@Override
	public User<DefaultMod, ?> getOwner() {
		return this.owner;
	}

	/**
	 * Sets the owner
	 * 
	 * @param owner the owner to set
	 */
	protected void setOwner(AbstractUser owner) {
		this.owner = owner;
	}

	@Override
	public Group<DefaultMod> getPrimaryGroup() {
		return this.primaryGroup;
	}

	/**
	 * Sets the primaryGroup
	 * 
	 * @param primaryGroup the primaryGroup to set
	 */
	protected void setPrimaryGroup(Group<DefaultMod> primaryGroup) {
		this.primaryGroup = primaryGroup;
	}
}

package com.temple.model.impl;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.temple.model.Group;
import com.temple.model.User;
import com.temple.model.impl.access.DefaultMod;
import com.temple.util.ToString;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractIntegerIdResource extends AbstractResource<Integer> {

	private static final long serialVersionUID = 1L;

	@ToString
	@Id
	private Integer id;

	/**
	 * Constructor.
	 */
	protected AbstractIntegerIdResource() {
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
	protected AbstractIntegerIdResource(DefaultMod accessRights, User<DefaultMod, ?> owner, Group<DefaultMod> primaryGroup) {
		super(accessRights, owner, primaryGroup);
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param id
	 * @param accessRights
	 * @param owner
	 * @param primaryGroup
	 */
	protected AbstractIntegerIdResource(int id, DefaultMod accessRights, User<DefaultMod, ?> owner, Group<DefaultMod> primaryGroup) {
		this(accessRights, owner, primaryGroup);
		this.id = Integer.valueOf(id);
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	protected void setId(Integer id) {
		this.id = id;
	}
}

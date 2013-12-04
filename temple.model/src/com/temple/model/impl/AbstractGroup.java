/**
 * 
 */
package com.temple.model.impl;

import javax.persistence.AssociationOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;

import com.temple.model.Group;
import com.temple.model.impl.access.DefaultMod;

/**
 * @author Florent
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// A group's primary group is itself
@AssociationOverride(name = "primaryGroup", joinColumns = @JoinColumn(name = "id", insertable = false, updatable = false))
public abstract class AbstractGroup extends AbstractIntegerIdResource implements Group<DefaultMod> {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(nullable = false, updatable = false, unique = true)
	private String name;

	protected AbstractGroup() {
		super();
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param accessRights
	 * @param owner
	 * @param name
	 */
	protected AbstractGroup(DefaultMod accessRights, AbstractUser owner, String name) {
		super(accessRights, owner, null);
		this.setPrimaryGroup(this);
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Object naturalKey() {
		return this.name;
	}
}

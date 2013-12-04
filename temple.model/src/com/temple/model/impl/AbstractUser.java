package com.temple.model.impl;

import java.util.Calendar;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.temple.model.User;
import com.temple.model.impl.access.DefaultMod;
import com.temple.model.impl.access.DefaultRole;

/**
 * @author Florent
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// a user is their own owner
@AssociationOverrides({ @AssociationOverride(name = "primaryGroup", joinColumns = @JoinColumn(name = "id", insertable = false, updatable = false)),
		@AssociationOverride(name = "owner", joinColumns = @JoinColumn(name = "id", insertable = false, updatable = false)) })
public abstract class AbstractUser extends AbstractGroup implements User<DefaultMod, DefaultRole> {

	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	private Calendar lastSignIn;

	@Column(nullable = false, updatable = false, unique = true)
	private String email;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private DefaultRole role;

	protected AbstractUser() {
		super();
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param accessRights
	 * @param name
	 * @param email
	 * @param role
	 */
	protected AbstractUser(DefaultMod accessRights, String name, String email, DefaultRole role) {
		super(accessRights, null, name);
		this.setOwner(this);
		this.email = email;
		this.role = role;
		this.lastSignIn = null;
	}

	/**
	 * @return the lastSignIn
	 */
	public Calendar getLastSignIn() {
		return this.lastSignIn;
	}

	/**
	 * Sets the lastSignIn
	 * 
	 * @param lastSignIn the lastSignIn to set
	 */
	public void setLastSignIn(Calendar lastSignIn) {
		this.lastSignIn = lastSignIn;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets the email
	 * 
	 * @param email the email to set
	 */
	protected void setEmail(String email) {
		this.email = email;
	}

	@Override
	public DefaultRole getRole() {
		return this.role;
	}

	/**
	 * Sets the role
	 * 
	 * @param role the role to set
	 */
	protected void setRole(DefaultRole role) {
		this.role = role;
	}
}

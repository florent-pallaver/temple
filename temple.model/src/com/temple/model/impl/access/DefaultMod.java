package com.temple.model.impl.access;

import com.temple.Module;
import com.temple.model.access.AccessRights;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@Embeddable
public class DefaultMod implements AccessRights<DefaultAccessType, DefaultRealm> {

	/**
	 * TODOC
	 */
	protected static final int NO_ACCESS_INT_MOD = 0;

	static final String localeKey = DefaultMod.class.getName();

	private static final long serialVersionUID = 1L;

	private static final int offset = DefaultAccessType.values().length;

	@Column(nullable = false)
	private int accessRights;

	/**
	 * Constructor.
	 * TODOC
	 */
	public DefaultMod() {
		this(DefaultMod.NO_ACCESS_INT_MOD);
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param mod
	 */
	protected DefaultMod(int mod) {
		super();
		this.accessRights = mod;
	}

	/**
	 * Constructor.
	 * TODOC
	 * 
	 * @param allowedAccesses
	 * @param allow
	 */
	public DefaultMod(AccessRightsEnum other, AccessRightsEnum signed, AccessRightsEnum group, AccessRightsEnum owner) {
		this(DefaultMod.NO_ACCESS_INT_MOD);
		for (final DefaultAccessType a : other.getAccessTypes()) {
			this.allow(DefaultRealm.OTHER, a, true);
		}
	}

	@Override
	public synchronized void allow(DefaultRealm r, DefaultAccessType a, boolean allow) {
		final int mask = 1 << DefaultMod.offset * r.ordinal() + a.ordinal();
		this.accessRights = allow ? this.accessRights | mask : this.accessRights & ~mask;
	}

	@Override
	public boolean allows(DefaultRealm r, DefaultAccessType a) {
		return (this.accessRights >> DefaultMod.offset * r.ordinal() + a.ordinal() & 1) == 1;
	}

	@Override
	public boolean allowsForAll(DefaultAccessType a) {
		boolean b = true;
		final DefaultRealm[] values = DefaultRealm.values();
		for (int i = values.length; i-- > 0;) {
			b = b && this.allows(values[i], a);
			if (!b) {
				i = 0;
			}
		}
		return b;
	}

	@Override
	public String getLocaleKey() {
		return DefaultMod.localeKey;
	}

	@Override
	public Serializable[] getLocaleParameters() {
		final DefaultRealm[] realms = DefaultRealm.values();
		final AccessRightsEnum[] privileges = new AccessRightsEnum[realms.length];
		final AccessRightsEnum[] values = AccessRightsEnum.values();
		for (final DefaultRealm r : realms) {
			final int o = (this.allows(r, DefaultAccessType.READ) ? 1 << DefaultAccessType.READ.ordinal() : 0)
					+ (this.allows(r, DefaultAccessType.WRITE) ? 1 << DefaultAccessType.WRITE.ordinal() : 0);
			privileges[r.ordinal()] = values[o];
		}
		return privileges;
	}

	@Override
	public Module getBundle() {
		return Module.MODEL;
	}

	@Override
	public DefaultMod clone() {
		DefaultMod c;
		try {
			c = (DefaultMod) super.clone();
		} catch (final CloneNotSupportedException e) {
			Module.MODEL.logger.warning(e.getMessage());
			c = new DefaultMod(this.accessRights);
		}
		return c;
	}

	@Override
	public int hashCode() {
		return 31 + this.accessRights;
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || obj instanceof DefaultMod && this.accessRights == ((DefaultMod) obj).accessRights;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}

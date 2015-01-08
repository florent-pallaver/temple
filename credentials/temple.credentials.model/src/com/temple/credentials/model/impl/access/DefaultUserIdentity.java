package com.temple.credentials.model.impl.access;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.temple.credentials.model.access.UserIdentity;
import com.temple.util.TempleUtil;
import com.temple.util.ToString;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
@Entity
@Table(name = DefaultUserIdentity.TABLE_NAME, indexes = { @Index(name = DefaultUserIdentity.USER_ID_INDEX_NAME,
columnList = DefaultUserIdentity.USER_ID_INDEX_COLUMN_LIST, unique = true) })
public class DefaultUserIdentity implements UserIdentity {

	static final String TABLE_NAME = "user_identities";

	static final String USER_ID_INDEX_COLUMN_LIST = "USER_ID";

	static final String USER_ID_INDEX_NAME = "U_" + DefaultUserIdentity.TABLE_NAME + "_" + DefaultUserIdentity.USER_ID_INDEX_COLUMN_LIST;

	static final int LOGIN_MAX_LENGTH = 32;

	static final int PASSWORD_MAX_LENGTH = 88;

	static final int SALT_MAX_LENGTH = 128;

	private static final long serialVersionUID = 1L;

	@ToString
	@Id
	@Column(nullable = false, updatable = false, length = DefaultUserIdentity.LOGIN_MAX_LENGTH)
	private String login;

	@Column(nullable = false, length = DefaultUserIdentity.PASSWORD_MAX_LENGTH)
	private String pass;

	@Column(nullable = false, updatable = false, length = DefaultUserIdentity.SALT_MAX_LENGTH)
	private String salt;

	@ToString
	@Column(name = DefaultUserIdentity.USER_ID_INDEX_COLUMN_LIST, nullable = false, updatable = false)
	private int userId;

	protected DefaultUserIdentity() {}

	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param login
	 * @param encryptedPass
	 * @param salt
	 */
	public DefaultUserIdentity(String login, String encryptedPass, String salt, int userId) {
		super();
		this.login = login;
		this.pass = encryptedPass;
		this.salt = salt;
		this.userId = userId;
	}

	@Override
	public String getLogin() {
		return this.login;
	}

	@Override
	public String getEncryptedPass() {
		return this.pass;
	}

	@Override
	public void setEncryptedPass(String pass) {
		this.pass = pass;
	}

	/**
	 * @return the salt
	 */
	public String getSalt() {
		return this.salt;
	}

	@Override
	public int getUserId() {
		return this.userId;
	}

	@Override
	public int hashCode() {
		return 31 + this.userId;
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || obj instanceof DefaultUserIdentity && this.userId == ((DefaultUserIdentity) obj).userId;
	}

	@Override
	public String toString() {
		return TempleUtil.toString(this);
	}
}

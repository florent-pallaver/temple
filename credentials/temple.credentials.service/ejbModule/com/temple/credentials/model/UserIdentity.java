package com.temple.credentials.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.temple.util.TempleUtil;
import com.temple.util.ToString;
import java.io.Serializable;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
@Entity
@Table(name = UserIdentity.TABLE_NAME, indexes = { @Index(name = UserIdentity.USER_ID_INDEX_NAME,
columnList = UserIdentity.USER_ID_INDEX_COLUMN_LIST, unique = true) })
public class UserIdentity implements Serializable {

	static final String TABLE_NAME = "user_identities";

	static final String USER_ID_INDEX_COLUMN_LIST = "USER_ID";

	static final String USER_ID_INDEX_NAME = "U_" + UserIdentity.TABLE_NAME + "_" + UserIdentity.USER_ID_INDEX_COLUMN_LIST;

	static final int LOGIN_MAX_LENGTH = 32;

	static final int PASSWORD_MAX_LENGTH = 88;

	static final int SALT_MAX_LENGTH = 128;

	private static final long serialVersionUID = 1L;

	@ToString
	@Id
	@Column(nullable = false, updatable = false, length = UserIdentity.LOGIN_MAX_LENGTH)
	private String login;

	@Column(nullable = false, length = UserIdentity.PASSWORD_MAX_LENGTH)
	private String pass;

	@Column(nullable = false, updatable = false, length = UserIdentity.SALT_MAX_LENGTH)
	private String salt;

	@ToString
	@Column(name = UserIdentity.USER_ID_INDEX_COLUMN_LIST, nullable = false, updatable = false)
	private int userId;

	protected UserIdentity() {}

	/**
	 * Constructor.
	 * TODOC
	 *
	 * @param login
	 * @param encryptedPass
	 * @param salt
	 * @param userId
	 */
	public UserIdentity(String login, String encryptedPass, String salt, int userId) {
		super();
		this.login = login;
		this.pass = encryptedPass;
		this.salt = salt;
		this.userId = userId;
	}

	/**
	 * @return the login (or user name) used to authenticate the user linked to this identity.
	 */
	public String getLogin() {
		return this.login;
	}

	/**
	 * @return the encrypted pass
	 */
	public String getEncryptedPass() {
		return this.pass;
	}

	/**
	 * Sets the encrypted pass
	 *
	 * @param pass the encrypted pass to set
	 */
	public void setEncryptedPass(String pass) {
		this.pass = pass;
	}

	/**
	 * @return the salt
	 */
	public String getSalt() {
		return this.salt;
	}

	/**
	 * @return the id of the user linked to this identity
	 */
	public int getUserId() {
		return this.userId;
	}

	@Override
	public int hashCode() {
		return 31 + this.userId;
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || obj instanceof UserIdentity && this.userId == ((UserIdentity) obj).userId;
	}

	@Override
	public String toString() {
		return TempleUtil.toString(this);
	}
}

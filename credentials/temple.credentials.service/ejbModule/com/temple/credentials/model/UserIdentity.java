package com.temple.credentials.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.temple.util.TempleUtil;
import com.temple.util.ToString;
import com.temple.util.security.Security;
import com.temple.util.security.UDCryptAlgorithm;

@Entity
@Table(name = UserIdentity.TABLE_NAME, indexes = {
		@Index(name = UserIdentity.USER_ID_INDEX_NAME, columnList = UserIdentity.USER_ID_INDEX_COLUMN_LIST, unique = true) })
public class UserIdentity implements PassProtected, Serializable {

	static final String TABLE_NAME = "user_identities";

	static final String USER_ID_INDEX_COLUMN_LIST = "USER_ID";

	static final String USER_ID_INDEX_NAME = "U_" + UserIdentity.TABLE_NAME + "_" + UserIdentity.USER_ID_INDEX_COLUMN_LIST;

	static final int LOGIN_MAX_LENGTH = 32;

	static final int PASSWORD_MAX_LENGTH = 88;

	static final int SALT_MAX_LENGTH = 128;

	private static final long serialVersionUID = 1L;

	private static final int SALT_LENGTH = 32;

	private static final UDCryptAlgorithm hasher = Security.SHA512CryptAlgorithm.instance;

	@ToString
	@Column(name = UserIdentity.USER_ID_INDEX_COLUMN_LIST, nullable = false, updatable = false)
	private int userId;

	@ToString
	@Id
	@Column(nullable = false, updatable = false, length = UserIdentity.LOGIN_MAX_LENGTH)
	private String login;

	@Column(nullable = false, length = UserIdentity.PASSWORD_MAX_LENGTH)
	private String pass;

	@Column(nullable = false, length = UserIdentity.SALT_MAX_LENGTH)
	private String salt;

	protected UserIdentity() {
	}

	public UserIdentity(String login, String rawPass, int userId) {
		super();
		this.userId = userId;
		this.login = login;
		this.setPass(rawPass);
	}

	public int getUserId() {
		return this.userId;
	}

	/**
	 * @return the login (or user name) used to authenticate the user linked to
	 *         this identity.
	 */
	public String getLogin() {
		return this.login;
	}

	@Override
	public String getPassHash() {
		return this.pass;
	}

	@Override
	public String getSalt() {
		return this.salt;
	}

	@Override
	public void setPass(String pass) {
		this.setPass(pass, null);
	}

	@Override
	public void setPass(String pass, String salt) {
		this.salt = salt == null ? TempleUtil.base64Encode(Security.randomBytes(UserIdentity.SALT_LENGTH)) : salt;
		this.pass = this.hash(pass);
	}

	@Override
	public boolean matchesPass(String pass) {
		return this.pass.equals(this.hash(pass));
	}

	private String hash(String pass) {
		return UserIdentity.hasher.encrypt64(this.salt, pass, this.login);
	}

	@Override
	public int hashCode() {
		return 97 + this.userId;
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

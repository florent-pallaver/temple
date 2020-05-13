/**
 *
 */
package com.temple.service.session.auth;

import java.io.Serializable;
import java.net.Inet4Address;

import com.temple.model.TempleUser;

/**
 * @author flominou
 *
 */
public final class AuthToken implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * In millis
	 */
	private static long EXPIRATION_DELAY = 30 * 60 * 1000;

	private final int id;

	private final long timestamp;

	private final int ip;

	// private String key ;

	private AuthToken(int id, long timestamp, int ip) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.ip = ip;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 *
	 * @return TODOC
	 * @throws TokenExpiredException
	 */
	public AuthToken next() throws TokenExpiredException {
		final long now = System.currentTimeMillis();
		if (now - this.timestamp < AuthToken.EXPIRATION_DELAY) {
			return new AuthToken(this.id, now, this.ip);
		}
		throw new TokenExpiredException();
	}

	public static AuthToken create(TempleUser user, Inet4Address ip) {
		return new AuthToken(user.getId(), System.currentTimeMillis(), AuthToken.toInt(ip));
	}

	private static int toInt(Inet4Address ip) {
		final byte[] address = ip.getAddress();
		return address[0] << 24 | address[1] << 16 | address[2] << 8 | address[3];
	}

}

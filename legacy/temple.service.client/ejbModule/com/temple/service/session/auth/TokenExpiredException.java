/**
 *
 */
package com.temple.service.session.auth;

import com.temple.service.session.SessionException;

/**
 * Thrown whenever an {@link AuthToken} has expired.
 *
 * @author flominou
 * @see AuthToken
 */
public final class TokenExpiredException extends SessionException {

	private static final long serialVersionUID = 1L;

}

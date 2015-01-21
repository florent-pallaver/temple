package com.temple.credentials.service.ejb;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.temple.credentials.service.CreateUserIdentityException;
import com.temple.credentials.service.CredentialsManager;
import com.temple.credentials.service.IncorrectPassException;
import com.temple.credentials.service.LoginNotFoundException;
import com.temple.credentials.service.UpdateUserIdentityException;
import com.temple.credentials.model.DefaultUserIdentity;
import com.temple.credentials.model.DefaultUserIdentity_;
import com.temple.util.TempleUtil;
import com.temple.util.security.Security;

/**
 * {@link CredentialsManager} implementation.
 *
 * @author Florent Pallaver
 * @version 1.0
 */
@Stateless(name = CredentialsManager.BEAN_NAME)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class CredentialsManagerBean implements CredentialsManager {

	private static final long serialVersionUID = 1L;

	private static final int SALT_LENGTH = 32;

	private static final String persistenceUnitName = "com.temple.credentials.persistenceUnit";

	@PersistenceContext(unitName = CredentialsManagerBean.persistenceUnitName)
	private EntityManager em;

	@Override
	public int findUserId(String login, String pass) throws LoginNotFoundException, IncorrectPassException {
		final DefaultUserIdentity ui = this.em.find(DefaultUserIdentity.class, login);
		if (ui == null) {
			throw new LoginNotFoundException(login);
		}
		this.checkPassword(ui, pass);
		return ui.getUserId();
	}

	@Override
	public void createIdentity(String login, String rawPass, int userId) throws CreateUserIdentityException {
		final String salt = TempleUtil.base64Encode(new String(Security.randomCharArray(CredentialsManagerBean.SALT_LENGTH)));
		final String crypt = this.crypt(login, salt, rawPass);
		final DefaultUserIdentity ui = new DefaultUserIdentity(login, crypt, salt, userId);
		try {
			this.em.persist(ui);
			this.em.flush(); // to be sure to catch every exception
		} catch (final Exception e) {
			throw new CreateUserIdentityException(login, e);
		}
	}

	@Override
	public void updatePass(Integer userId, String current, String nevv) throws IncorrectPassException, UpdateUserIdentityException {
		final CriteriaBuilder cb = this.em.getCriteriaBuilder();
		final CriteriaQuery<DefaultUserIdentity> cq = cb.createQuery(DefaultUserIdentity.class);
		cq.where(cb.equal(cq.from(DefaultUserIdentity.class).get(DefaultUserIdentity_.userId), userId));
		try {
			final DefaultUserIdentity ui = this.em.createQuery(cq).getSingleResult();
			this.checkPassword(ui, current);
			ui.setEncryptedPass(this.crypt(ui.getLogin(), ui.getSalt(), nevv));
			this.em.merge(ui);
			this.em.flush(); // to be sure to catch every exception
		} catch (final IncorrectPassException e) {
			throw e;
		} catch (final Exception e) {
			throw new UpdateUserIdentityException(e);
		}
	}

	private void checkPassword(DefaultUserIdentity ui, String pass) throws IncorrectPassException {
		if (!ui.getEncryptedPass().equals(this.crypt(ui.getLogin(), ui.getSalt(), pass))) {
			throw new IncorrectPassException();
		}
	}

	private String crypt(String login, String salt, String rawPass) {
		return Security.SHA512Base64CryptAlgorithm.instance.encrypt(salt + rawPass + login);
	}
}

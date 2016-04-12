package com.temple.credentials.service.ejb;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.temple.credentials.model.UserIdentity;
import com.temple.credentials.model.UserIdentity_;
import com.temple.credentials.service.CreateUserIdentityException;
import com.temple.credentials.service.CredentialsManager;
import com.temple.credentials.service.IncorrectPassException;
import com.temple.credentials.service.LoginNotFoundException;
import com.temple.credentials.service.UpdateUserIdentityException;

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

	private static final String persistenceUnitName = "com.temple.credentials.persistenceUnit";

	@PersistenceContext(unitName = CredentialsManagerBean.persistenceUnitName)
	private EntityManager em;

	@Override
	public int findUserId(String login, String pass) throws LoginNotFoundException, IncorrectPassException {
		final UserIdentity ui = this.em.find(UserIdentity.class, login);
		if (ui == null) {
			throw new LoginNotFoundException(login);
		}
		this.checkPassword(ui, pass);
		return ui.getUserId();
	}

	@Override
	public void createIdentity(String login, String rawPass, int userId) throws CreateUserIdentityException {
		final UserIdentity ui = new UserIdentity(login, rawPass, userId);
		try {
			this.em.persist(ui);
			this.em.flush(); // to be sure to catch every exception
		} catch (final Exception e) {
			throw new CreateUserIdentityException(login, e);
		}
	}

	@Override
	public void updatePass(int userId, String current, String newPass) throws IncorrectPassException, UpdateUserIdentityException {
		final CriteriaBuilder cb = this.em.getCriteriaBuilder();
		final CriteriaQuery<UserIdentity> cq = cb.createQuery(UserIdentity.class);
		cq.where(cb.equal(cq.from(UserIdentity.class).get(UserIdentity_.userId), userId));
		try {
			final UserIdentity ui = this.em.createQuery(cq).getSingleResult();
			this.checkPassword(ui, current);
			ui.setPass(newPass);
			this.em.merge(ui);
			this.em.flush(); // to be sure to catch every exception
		} catch (final IncorrectPassException e) {
			throw e;
		} catch (final Exception e) {
			throw new UpdateUserIdentityException(e);
		}
	}

	private void checkPassword(UserIdentity ui, String pass) throws IncorrectPassException {
		if (!ui.matchesPass(pass)) {
			throw new IncorrectPassException();
		}
	}
}

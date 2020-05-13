package diet.service;

import java.io.Serializable;
import java.util.Objects;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.criteria.Root;

import diet.model.Owned;
import diet.model.User;
import diet.model.User_;

@SessionScoped
public class SessionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Instance<EntityManagerBean> emBean;
	
	private User currentUser = null;
	
	SessionBean() {}

	public User getUser() throws ServiceException {
		this.checkIsSignedIn();
		return this.currentUser;
	}

	public boolean isSignedIn() {
		return this.currentUser != null;
	}
	
	public void checkIsSignedIn() throws ServiceException {
		if(currentUser == null) {
			throw new ServiceException("Please sign in in order to use the application!");
		}
	}
	
	public void checkOwner(Owned owned) throws ServiceException {
		this.checkIsSignedIn();
		if(!this.currentUser.equals(owned.getOwner())) {
			throw new ServiceException("This %s is not yours!!!! :(", owned.getClass().getSimpleName());
		}
	}
	
	public void signIn(String userName, String pass) throws ServiceException {
		if(Objects.equals(userName, pass)) {
			try {
				final User user = this.emBean.get().findOne(User.class, (cb, cq) -> {
					final Root<User> root = cq.from(User.class);
					cq.where(cb.equal(cb.lower(root.get(User_.name)), userName.toLowerCase()));
				});
				this.currentUser = user;
			} catch(Exception e) {
				throw new ServiceException(e, "User \'%s\' not found. Check the spelling!", userName);
			}
		} else {
			throw new ServiceException("user name and pass did not match!");
		}
	}
	
	public void signOut() {
		this.currentUser = null;
	}
	
}

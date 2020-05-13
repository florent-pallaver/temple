package diet.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import diet.model.User;

@Path("session")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@AutoSignedIn
@RequestScoped
public class SessionResource {

	private SessionBean sessionBean;

	SessionResource() {}

	@Inject
	SessionResource(SessionBean sessionBean) {
		this.sessionBean = sessionBean;
	}
	
	@GET
	public User getUser() throws ServiceException {
		return this.sessionBean.getUser();
	}
	
	@POST
	public User signIn(SignInData data) throws ServiceException {
		this.sessionBean.signIn(data.name, data.pass);
		return this.sessionBean.getUser();
	}
	
	@DELETE
	public void signOut() {
		sessionBean.signOut();
	}
}

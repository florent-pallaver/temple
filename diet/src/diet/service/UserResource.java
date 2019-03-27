package diet.service;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import diet.model.User;
import diet.model.User_;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource extends AbstractBean<User> {

	UserResource() {
		super(User.class);
	}

	@GET
	public List<User> getAll() {
		return super.getAll((cb, root) -> Arrays.asList(cb.asc(root.get(User_.name))));
	}

}

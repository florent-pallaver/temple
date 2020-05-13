package diet.service;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import diet.model.Food;
import diet.model.User;
import diet.model.User_;

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource extends AbstractBean<User> {

	@Inject
	private SessionBean sessionBean;
	
	UserResource() {
		super(User.class);
	}

	@GET
	public List<User> getAll() {
		return super.getAll((cb, root) -> Arrays.asList(cb.asc(root.get(User_.name))));
	}

	@PUT
	@Path("scores/food/{foodId}/{score}")
	public void setFoodScore(@PathParam("foodId") int foodId, @PathParam("score") int score) throws ServiceException {
		final User user = this.sessionBean.getUser();
		final Food food = this.emBean.get(Food.class, foodId);
		user.setScore(food, score);
		this.emBean.merge(user);
	}
	
}

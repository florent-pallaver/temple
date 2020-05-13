package diet.service;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import diet.model.Food;
import diet.model.FoodData;
import diet.model.FoodData_;

@Path("food")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
@AutoSignedIn
public class FoodResource extends AbstractBean<Food> {

	@Inject
	private SessionBean sessionBean;
	
	FoodResource() {
		super(Food.class);
	}

	@GET
	public List<Food> getAll() throws ServiceException {
		final List<Food> all = super.getAll((cb, root) -> Arrays.asList(
				cb.asc(root.get(FoodData_.name)),
				cb.asc(root.get(FoodData_.brand))
				));
		
		if(sessionBean.isSignedIn()) {
			all.sort(sessionBean.getUser());
		}
		return all;
	}

	@GET
	@Path("{foodType}")
	public List<Food> getAll(@BeanParam FoodFilter filter) {
		return super.getAll_(filter);
	}

	@POST
	public void create(FoodData foodData) throws ServiceException {
		super.create(new Food(foodData));
	}

	@PUT
	@Path("{foodId}")
	public void update(@PathParam("foodId") int foodId, FoodData foodData) {
		final Food food = this.get(foodId);
		if(food != null) {
			food.set(foodData);
			this.emBean.merge(food);
		}
	}

	@DELETE
	public void delete(@QueryParam("id") List<Integer> foodIds) throws ServiceException {
		this.sessionBean.checkIsSignedIn();
		foodIds.forEach(super::delete);
	}

}

package diet.service;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import diet.model.Meal;
import diet.model.Meal_;

@Path("meal")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class MealResource extends AbstractResource<Meal> {

	MealResource() {
		super(Meal.class);
	}

	@GET
	public List<Meal> getAll() {
		return super.getAll((cb, root) -> Arrays.asList(cb.desc(root.get(Meal_.eatDate))));
	}

	@Override
	@POST
	public void create(Meal meal) {
		super.create(meal);
	}

	@Override
	@DELETE
	public void delete(int mealId) {
		super.delete(mealId);
	}

}

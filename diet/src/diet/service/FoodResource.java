package diet.service;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
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
public class FoodResource extends AbstractResource<Food> {

	FoodResource() {
		super(Food.class);
	}

	@GET
	public List<Food> getAll() {
		return super.getAll((cb, root) -> Arrays.asList(
				cb.asc(root.get(FoodData_.name)),
				cb.asc(root.get(FoodData_.brand))
				));
	}

	@POST
	@Transactional(TxType.REQUIRES_NEW)
	public void create(FoodData foodData) {
		super.create(new Food(foodData));
	}

	@PUT
	@Path("{foodId}")
	@Transactional(TxType.REQUIRES_NEW)
	public void update(@PathParam("foodId") int foodId, FoodData foodData) {
		final Food food = this.get(foodId);
		if(food != null) {
			food.set(foodData);
			this.em.merge(food);
		}
	}

	@DELETE
	@Transactional(TxType.REQUIRES_NEW)
	public void delete(@QueryParam("id") List<Integer> foodIds) {
		foodIds.forEach(super::delete);
	}

}

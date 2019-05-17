package diet.service;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.criteria.Root;
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
import diet.model.FoodType;
import diet.model.Food_;

@Path("food")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class FoodResource extends AbstractBean<Food> {

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

	@GET
	@Path("{foodType}")
	public List<Food> getAll(@PathParam("foodType") FoodType foodType) {
		return super.getAll_((cb, cq) -> { 
			final Root<Food> root = cq.from(Food.class);
			cq.where(cb.equal(root.get(Food_.type), foodType))
				.orderBy(
					cb.asc(root.get(FoodData_.name)),
					cb.asc(root.get(FoodData_.brand))
				);
		});
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
	public void delete(@QueryParam("id") List<Integer> foodIds) {
		foodIds.forEach(super::delete);
	}
}

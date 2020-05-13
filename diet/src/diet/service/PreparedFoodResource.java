package diet.service;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import diet.model.Food;
import diet.model.PreparedFood;
import diet.model.PreparedFoodData;

@Path("dish")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
@AutoSignedIn
public class PreparedFoodResource extends AbstractBean<PreparedFood> {
	
//	@Inject
//	private SessionBean sessionBean;
	
	public PreparedFoodResource() {
		super(PreparedFood.class);
	}
	
	@GET
	@Path("preparedFood")
	public List<PreparedFood> getAll() {
		return this.getAll((cq, x) -> Arrays.asList());
	}
	
	@POST
	@Path("preparedFood/{foodId}")
	public void create(@PathParam("foodId") int foodId, PreparedFoodData data) throws ServiceException {
		final Food food = this.emBean.getReference(Food.class, foodId);
		final PreparedFood preparedFood = new PreparedFood(food, data);
		try {
			super.create(preparedFood);
		} catch (Exception e) {
			throw new ServiceException(e, "Could not create prepared food!");
		}
	}
	
}

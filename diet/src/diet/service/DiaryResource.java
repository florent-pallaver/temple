package diet.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import diet.model.Food;
import diet.model.Meal;
import diet.model.MealTime;
import diet.model.SleepData;
import diet.model.User;
import diet.model.UserDay;
import diet.model.UserDayData;
import diet.model.UserDayData_;
import diet.model.UserDay_;

@Path("diary")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class DiaryResource extends AbstractBean<UserDay> {
	
	@Inject
	private SessionBean sessionBean;
	
	DiaryResource() {
		super(UserDay.class);
	}

	@GET
	public List<UserDay> getDays() throws ServiceException {
		final User user = this.sessionBean.getUser();
		return this.emBean.getAll(UserDay.class, (cb, cq) -> {
			final Root<UserDay> root = cq.from(UserDay.class);
			cq.where(cb.equal(root.get(UserDay_.user), user))
				.orderBy(cb.desc(root.get(UserDayData_.dayDate)));
		});
	}
	
	@POST
	public void addDay(DateBean dateBean) throws ServiceException {
		final User user = this.sessionBean.getUser();
		final LocalDate date = dateBean.getDate();
		final UserDay userDay = new UserDay(date, user);
		try {
			super.create(userDay);
		} catch(Exception e) {
			throw new ServiceException(e, "The UserDay at date %s could not be created. Maybe it already exists...", date.format(DateTimeFormatter.ISO_LOCAL_DATE));
		}
	}
	
	@PUT
	@Path("{dayId}")
	public void setDayData(@PathParam("dayId") int dayId, UserDayData data) throws ServiceException {
		try {
			final UserDay userDay = this.getDay(dayId);
			userDay.set(data);
			this.emBean.merge(userDay);
		} catch(Exception e) {
			throw new ServiceException(e, "The UserDay could not be updated.");
		}
	}
	
	private UserDay getDay(int dayId) throws ServiceException {
		final UserDay userDay = this.get(dayId);
		this.sessionBean.checkOwner(userDay);
		return userDay;
	}
	
	@GET
	@Path("{dayId}/meals")	
	public List<Meal> getDayMeals(@PathParam("dayId") int dayId) throws ServiceException {
		return this.getDay(dayId).getMeals();
	}
	
	@PUT
	@Path("{dayId}/meals/{time}")
	public List<Meal> setDayMeal(@PathParam("dayId") int dayId, @PathParam("time") MealTime mealTime, Map<String, Integer> foodQuantities) throws ServiceException {
		
		try {
			final Meal meal = this.getDay(dayId).getMeal(mealTime);
			final Map<Food, Integer> quantities = foodQuantities.keySet().stream()
				.map(Integer::valueOf)
				.map(id -> this.emBean.getReference(Food.class, id))
				.collect(Collectors.toMap(Function.identity(), food -> foodQuantities.get(Integer.toString(food.getId()))));
			meal.setFoodQuantities(quantities);
			this.emBean.merge(meal);
		} catch(Exception e) {
			throw new ServiceException(e, "The %s could not be updated.", mealTime);
		}
		
		return this.get(dayId).getMeals();
	}
	
	@PUT
	@Path("{date}/sleep")
	public void setSleep(SleepData data) {
		// TODO
	}
	
}

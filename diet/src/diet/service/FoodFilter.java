package diet.service;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import diet.model.FoodType;

public class FoodFilter {

	@PathParam("foodType")
	private FoodType foodType;
	
	@QueryParam("search")
	@DefaultValue("")
	private String search;
	
}

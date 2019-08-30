package diet.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import diet.model.Food;
import diet.model.FoodData_;
import diet.model.FoodType;
import diet.model.Food_;

public class FoodFilter implements BiConsumer<CriteriaBuilder, CriteriaQuery<Food>>{

	@PathParam("foodType")
	private FoodType foodType;
	
	@QueryParam("search")
	private String search;

	private void clean() {
		if(this.search != null) {
			final String s = search.trim();
			if(s.isEmpty()) {
				this.search = null;
			} else {
				this.search = '%' + s + '%';
			}
		}
	}
	
	@Override
	public void accept(CriteriaBuilder cb, CriteriaQuery<Food> query) {
		this.clean();
		
		final Root<Food> root = query.from(Food.class);
		
		final List<Predicate> conditions = new ArrayList<>();
		
		if(this.foodType != null) {
			conditions.add(cb.equal(root.get(Food_.type), this.foodType));
		}
		if(this.search != null) {
//			cb.and(where, cb.like(arg0, arg1))
		}
		final int conditionsCount = conditions.size();
		if(conditionsCount > 0) {
			query.where(conditions.toArray(new Predicate[conditionsCount]));
		}
		
		final List<Order> orders = Arrays.asList(
				cb.asc(root.get(FoodData_.name)),
				cb.asc(root.get(FoodData_.brand))
		);
		
		query.orderBy(orders);
	}
}

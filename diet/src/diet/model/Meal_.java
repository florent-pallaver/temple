package diet.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-03-19T23:19:30.796+0100")
@StaticMetamodel(Meal.class)
public class Meal_ {
	public static volatile SingularAttribute<Meal, Integer> id;
	public static volatile SingularAttribute<Meal, UserDay> day;
	public static volatile SingularAttribute<Meal, MealTime> time;
	public static volatile MapAttribute<Meal, Food, Integer> content;
}

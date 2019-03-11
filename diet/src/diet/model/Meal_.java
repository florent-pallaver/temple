package diet.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-03-08T00:36:49.982+0100")
@StaticMetamodel(Meal.class)
public class Meal_ {
	public static volatile SingularAttribute<Meal, Integer> id;
	public static volatile SingularAttribute<Meal, User> eater;
	public static volatile MapAttribute<Meal, Food, Integer> content;
	public static volatile SingularAttribute<Meal, Date> eatDate;
}

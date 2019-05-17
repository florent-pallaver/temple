package diet.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-10T00:40:26.035+0200")
@StaticMetamodel(Food.class)
public class Food_ extends FoodData_ {
	public static volatile SingularAttribute<Food, Integer> id;
	public static volatile SingularAttribute<Food, Date> creationDate;
	public static volatile SingularAttribute<Food, Date> lastUpdate;
}

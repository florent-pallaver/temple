package diet.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-03-19T00:48:28.214+0100")
@StaticMetamodel(UserDay.class)
public class UserDay_ extends UserDayData_ {
	public static volatile SingularAttribute<UserDay, Integer> id;
	public static volatile SingularAttribute<UserDay, User> user;
	public static volatile SingularAttribute<UserDay, Integer> sleepDuration;
	public static volatile ListAttribute<UserDay, Meal> meals;
}

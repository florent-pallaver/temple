package diet.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-24T01:11:05.363+0200")
@StaticMetamodel(UserDay.class)
public class UserDay_ extends UserDayData_ {
	public static volatile SingularAttribute<UserDay, Integer> id;
	public static volatile SingularAttribute<UserDay, User> user;
	public static volatile ListAttribute<UserDay, Meal> meals;
}

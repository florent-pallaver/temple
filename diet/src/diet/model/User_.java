package diet.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-03-19T23:19:31.035+0100")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, Double> weight;
	public static volatile SingularAttribute<User, Date> weighDate;
	public static volatile SingularAttribute<User, GrowthMode> growthMode;
}

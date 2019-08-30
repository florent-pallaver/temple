package diet.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-07-10T00:55:39.015+0200")
@StaticMetamodel(PreparedFood.class)
public class PreparedFood_ {
	public static volatile SingularAttribute<PreparedFood, Integer> id;
	public static volatile SingularAttribute<PreparedFood, String> name;
	public static volatile SingularAttribute<PreparedFood, User> owner;
	public static volatile SingularAttribute<PreparedFood, Date> creationDate;
	public static volatile SingularAttribute<PreparedFood, Food> food;
	public static volatile SingularAttribute<PreparedFood, Integer> rawQuantity;
	public static volatile SingularAttribute<PreparedFood, Integer> cookedQuantity;
	public static volatile SingularAttribute<PreparedFood, Integer> usedQuantity;
}

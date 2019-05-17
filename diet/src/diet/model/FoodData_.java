package diet.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-04-24T01:10:56.460+0200")
@StaticMetamodel(FoodData.class)
public class FoodData_ {
	public static volatile SingularAttribute<FoodData, String> name;
	public static volatile SingularAttribute<FoodData, String> brand;
	public static volatile SingularAttribute<FoodData, Intake> intake;
	public static volatile SingularAttribute<FoodData, FoodType> type;
	public static volatile SingularAttribute<FoodData, Counting> counting;
}

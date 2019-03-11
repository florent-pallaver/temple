package diet.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-03-05T01:10:11.282+0100")
@StaticMetamodel(FoodData.class)
public class FoodData_ {
	public static volatile SingularAttribute<FoodData, String> name;
	public static volatile SingularAttribute<FoodData, String> brand;
	public static volatile SingularAttribute<FoodData, Double> protein;
	public static volatile SingularAttribute<FoodData, Double> fat;
	public static volatile SingularAttribute<FoodData, Double> carb;
	public static volatile SingularAttribute<FoodData, Double> kcal;
	public static volatile SingularAttribute<FoodData, Double> ig;
}

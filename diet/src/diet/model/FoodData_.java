package diet.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-03-22T18:47:13.471+0100")
@StaticMetamodel(FoodData.class)
public class FoodData_ {
	public static volatile SingularAttribute<FoodData, String> name;
	public static volatile SingularAttribute<FoodData, String> brand;
	public static volatile SingularAttribute<FoodData, Intake> intake;
	public static volatile SingularAttribute<FoodData, FoodCounting> counting;
}

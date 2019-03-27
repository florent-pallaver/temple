package diet.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-03-21T00:46:13.380+0100")
@StaticMetamodel(UserDayData.class)
public class UserDayData_ {
	public static volatile SingularAttribute<UserDayData, LocalDate> dayDate;
	public static volatile SingularAttribute<UserDayData, Double> weight;
	public static volatile SingularAttribute<UserDayData, Integer> metabolicRate;
	public static volatile SingularAttribute<UserDayData, GrowthMode> growthMode;
	public static volatile SingularAttribute<UserDayData, Integer> metabolicFactor;
}

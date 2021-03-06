package diet.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-06-23T17:40:52.754+0200")
@StaticMetamodel(UserDayData.class)
public class UserDayData_ {
	public static volatile SingularAttribute<UserDayData, LocalDate> dayDate;
	public static volatile SingularAttribute<UserDayData, Double> weight;
	public static volatile SingularAttribute<UserDayData, Integer> metabolicRate;
	public static volatile SingularAttribute<UserDayData, GrowthMode> growthMode;
	public static volatile SingularAttribute<UserDayData, Integer> activityFactor;
	public static volatile SingularAttribute<UserDayData, Integer> fatFactor;
	public static volatile SingularAttribute<UserDayData, Integer> proteinFactor;
	public static volatile SingularAttribute<UserDayData, Integer> sleepDuration;
	public static volatile SingularAttribute<UserDayData, Integer> fasting;
	public static volatile SingularAttribute<UserDayData, UserDayResult> result;
	public static volatile SingularAttribute<UserDayData, String> comments;
}

package com.temple.model.impl;

import com.temple.model.impl.access.DefaultRole;
import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-10-17T22:32:02.760+1100")
@StaticMetamodel(AbstractUser.class)
public class AbstractUser_ extends AbstractGroup_ {
	public static volatile SingularAttribute<AbstractUser, Calendar> lastSignIn;
	public static volatile SingularAttribute<AbstractUser, String> email;
	public static volatile SingularAttribute<AbstractUser, DefaultRole> role;
}

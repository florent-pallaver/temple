package com.temple.model.impl;

import com.temple.model.impl.access.DefaultMod;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-10-25T14:37:43.633+1100")
@StaticMetamodel(AbstractResource.class)
public class AbstractResource_ extends AbstractEntity_ {
	public static volatile SingularAttribute<AbstractResource, DefaultMod> accessRights;
	public static volatile SingularAttribute<AbstractResource, AbstractUser> owner;
	public static volatile SingularAttribute<AbstractResource, AbstractGroup> primaryGroup;
}

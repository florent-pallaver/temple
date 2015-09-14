package com.temple.model.impl;

import com.temple.model.impl.access.DefaultMod;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-08-17T10:42:10.254+0200")
@StaticMetamodel(AbstractResource.class)
public class AbstractResource_ extends AbstractEntity_ {
	public static volatile SingularAttribute<AbstractResource, DefaultMod> accessRights;
	public static volatile SingularAttribute<AbstractResource, AbstractUser> owner;
	public static volatile SingularAttribute<AbstractResource, AbstractGroup> primaryGroup;
}

package com.temple.credentials.model.impl.access;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-01-08T10:48:12.466+0100")
@StaticMetamodel(DefaultUserIdentity.class)
public class DefaultUserIdentity_ {
	public static volatile SingularAttribute<DefaultUserIdentity, String> login;
	public static volatile SingularAttribute<DefaultUserIdentity, String> salt;
	public static volatile SingularAttribute<DefaultUserIdentity, Integer> userId;
	public static volatile SingularAttribute<DefaultUserIdentity, String> pass;
}

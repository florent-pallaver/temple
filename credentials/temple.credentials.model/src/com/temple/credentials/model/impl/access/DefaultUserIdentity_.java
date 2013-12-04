package com.temple.credentials.model.impl.access;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-10-17T19:18:41.558+1100")
@StaticMetamodel(DefaultUserIdentity.class)
public class DefaultUserIdentity_ {
	public static volatile SingularAttribute<DefaultUserIdentity, String> login;
	public static volatile SingularAttribute<DefaultUserIdentity, String> password;
	public static volatile SingularAttribute<DefaultUserIdentity, String> salt;
	public static volatile SingularAttribute<DefaultUserIdentity, Integer> userId;
}

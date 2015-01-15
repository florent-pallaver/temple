package com.temple.credentials.model.impl.access;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-01-10T01:45:13.785+0100")
@StaticMetamodel(DefaultUserIdentity.class)
public class DefaultUserIdentity_ {
	public static volatile SingularAttribute<DefaultUserIdentity, String> login;
	public static volatile SingularAttribute<DefaultUserIdentity, String> pass;
	public static volatile SingularAttribute<DefaultUserIdentity, String> salt;
	public static volatile SingularAttribute<DefaultUserIdentity, Integer> userId;
}

package com.temple.credentials.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-01-10T01:45:13.785+0100")
@StaticMetamodel(UserIdentity.class)
public class UserIdentity_ {
	public static volatile SingularAttribute<UserIdentity, String> login;
	public static volatile SingularAttribute<UserIdentity, String> pass;
	public static volatile SingularAttribute<UserIdentity, String> salt;
	public static volatile SingularAttribute<UserIdentity, Integer> userId;
}

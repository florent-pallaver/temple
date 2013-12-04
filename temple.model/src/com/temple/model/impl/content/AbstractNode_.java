package com.temple.model.impl.content;

import com.temple.model.impl.AbstractIntegerIdResource_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2013-10-17T14:18:02.411+1100")
@StaticMetamodel(AbstractNode.class)
public class AbstractNode_ extends AbstractIntegerIdResource_ {
	public static volatile SingularAttribute<AbstractNode, DefaultNodeKind> kind;
	public static volatile SingularAttribute<AbstractNode, String> radix;
	public static volatile SingularAttribute<AbstractNode, AbstractNode> parent;
}

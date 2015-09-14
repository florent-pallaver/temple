package com.temple.service.ejb;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.temple.model.impl.access.DefaultAccessType;
import com.temple.service.ejb.action.Action;
import com.temple.service.ejb.action.ActionType;
import com.temple.service.ejb.action.Property;
import com.temple.service.ejb.action.PropertyDescription;
import com.temple.service.ejb.action.constraint.ActionChecker;
import com.temple.service.ejb.action.constraint.ConstraintException;
import com.temple.service.ejb.action.impl.DefaultAction;
import com.temple.service.ejb.action.impl.DefaultProperty;
import com.temple.view.LocaleViewable;

/**
 * TODOC
 *
 * @author cr9z1k
 * @version 1.0
 */
// @Local
// @Singleton
public class ApplicationEJBHelper extends AbstractEJBBean {

	private static final long serialVersionUID = 1L;

	private final Map<? extends ActionType<DefaultAccessType>, ActionCreator> creators = new HashMap<>();

	private final Map<ActionType<DefaultAccessType>, ActionChecker> checkers = new HashMap<>();

	public <T extends ActionType<DefaultAccessType>> Action<?> createAction(T at, Serializable id) throws IllegalArgumentException {
		this.ensure(at);
		return this.creators.get(at).createAction(id);
	}

	public void checkConstraint(Action<? extends ActionType<DefaultAccessType>> a) throws ConstraintException {
		final ActionType<DefaultAccessType> type = a.getType();
		this.ensure(type);
		this.checkers.get(type).check(a);
	}

	private void ensure(ActionType<DefaultAccessType> at) {
		if (!this.creators.containsKey(at)) {
			// final Field f;
			// try {
			// // f = at.getClass().getField(at.toEnum().name());
			// } catch (Exception e) {
			// throw new IllegalArgumentException(e);
			// }
			// // this.creators.put(at, new ActionCreator(at));
			// // final Annotation[] as = f.getAnnotations();
			// final List<PropertiesChecker<?>> lc = new ArrayList<>();
			// for (Annotation a : as) {
			// final Class<? extends Annotation> ac = a.annotationType();
			// final Constraint c = ac.getAnnotation(Constraint.class);
			// if (c != null) {
			// try {
			// lc.add(c.value().getConstructor(String[].class, ac).newInstance(ac.getMethod(c.keysProperty()).invoke(a),
			// a));
			// } catch (Exception e) {
			// throw new IllegalArgumentException(e);
			// }
			// }
			// }
			// this.checkers.put(at, new ActionChecker(this.creators.get(at).propertiesKey, lc.toArray(new
			// PropertiesChecker<?>[lc.size()])));
			// final RolePolicy rp = f.getAnnotation(RolePolicy.class);
			// final Role r = rp == null ? maxOverridingRole : rp.value();
			// roleMap.put(at, r.compareTo(maxOverridingRole) > 0 ? maxOverridingRole : r);
			//
			// final PermissionPolicy fp = f.getAnnotation(PermissionPolicy.class);
			// familyMap.put(at, fp == null ? null : fp.value());
			//
			//
			// final RelationPolicy ep = f.getAnnotation(RelationPolicy.class);
			// relationMap.put(at, ep == null ? Relation.NONE : );
		}
	}

	static final class ActionCreator {

		private final ActionType<DefaultAccessType> actionType;

		private final String actionKey;

		private final String propertyKeyPrefix;

		final String[] propertiesKey;

		ActionCreator(ActionType<DefaultAccessType> at) {
			this.actionType = at;
			this.actionKey = at.key();
			this.propertyKeyPrefix = this.actionKey + LocaleViewable.DELIMITER;
			final PropertyDescription[] pds = at.propertiesDescription();
			final int pc = pds.length;
			this.propertiesKey = new String[pc];
			for (int i = pc; i-- > 0;) {
				this.propertiesKey[i] = pds[i].key();
			}
		}

		Action<?> createAction(Serializable id) {
			final int pl = this.propertiesKey.length;
			final Property[] ps = new Property[pl];
			for (int i = pl; i-- > 0;) {
				ps[i] = new DefaultProperty(this.propertyKeyPrefix + this.propertiesKey[i]);
			}
			return new DefaultAction<>(id, this.actionType, ps);
		}
	}
}

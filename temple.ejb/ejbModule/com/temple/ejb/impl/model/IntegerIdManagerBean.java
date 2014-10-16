package com.temple.ejb.impl.model;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.metamodel.ManagedType;

import com.temple.ejb.model.IdentifierManager;
import com.temple.model.GenerateIntegerId;
import com.temple.model.IdentifierGeneratorHelper;
import com.temple.model.TempleEntity;
import com.temple.model.filter.FindMaxFilter;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
@Startup
@Singleton
@Local(IdentifierManager.class)
public class IntegerIdManagerBean extends AbstractEntityManagerBean implements IdentifierManager {

	private static final long serialVersionUID = 1L;

	@EJB
	private IdentifierManager self;

	private final Map<Class<?>, Integer> lastIds = new HashMap<>();

	@Override
	@Lock(LockType.WRITE)
	public Integer newId(TempleEntity e) {
		final Class<?> c = e.getClass();
		final Integer nextId = Integer.valueOf(this.lastIds.get(c).intValue() + 1);
		this.lastIds.put(c, nextId);
		return nextId;
	}

	@Override
	@PostConstruct
	protected void postConstruct() {
		for (final ManagedType<?> mt : this.em.getMetamodel().getManagedTypes()) {
			final Class<?> mc = mt.getJavaType();
			final GenerateIntegerId a = mc.getAnnotation(GenerateIntegerId.class);
			if (a != null) {
				try {
					final FindMaxFilter<Integer> fma = a.value().newInstance();
					final Integer maxId = fma.createMaxQuery(this.em).getSingleResult();
					this.lastIds.put(mc, maxId == null ? Integer.valueOf(0) : maxId);
					this.info(mc + " " + maxId);
					IdentifierGeneratorHelper.instance.register(mc, this.self);
				} catch (InstantiationException | IllegalAccessException e) {
					this.error(e);
				}
			}
		}
		super.postConstruct();
	}
}

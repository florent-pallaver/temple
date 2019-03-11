package com.temple.service.ejb.action.constraint;

import java.io.Serializable;
import java.util.regex.Pattern;


/**
 * TODOC 
 * @author cr9z1k
 * @version 1.0
 */
public final class PatternConstraintChecker extends PropertiesChecker<PatternConstraint> {

	private final Pattern	pattern;
	private transient Object[] parameters ;
	
	/**
	 * Constructor.
	 * @param keys
	 * @param constraint
	 */
	public PatternConstraintChecker(String[] keys, PatternConstraint constraint) {
		super(keys, constraint);
		
		this.pattern = Pattern.compile(constraint.pattern());
	}

	@Override
	protected boolean isValid(Serializable s) {
		return (s instanceof String) && this.pattern.matcher((String)s).matches();
	}

	@Override
	protected Object[] getLocaleParameters() {
		if(this.parameters == null) {
			this.parameters = new Object[]{this.pattern.pattern()} ; 
		}
		return this.parameters;
	}
	
}

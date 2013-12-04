package com.temple.ejb.action;

import java.io.Serializable;

import com.temple.ejb.ApplicationEJB;
import com.temple.util.Valuable;
import com.temple.view.LocaleViewable;

/**
 * A property is used by an {@link Action} to describe how it must be {@link ApplicationEJB#execute(Action) executed}.
 * 
 * @author Florent Pallaver
 * @version 1.0
 * @see Action
 */
public interface Property extends Valuable<Serializable>, LocaleViewable {}

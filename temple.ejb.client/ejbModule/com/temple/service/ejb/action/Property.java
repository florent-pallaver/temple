package com.temple.service.ejb.action;

import java.io.Serializable;

import com.temple.util.Valuable;
import com.temple.view.LocaleViewable;

/**
 * A property is used by an {@link Action} to describe how it must be executed.
 *
 * @author Florent Pallaver
 * @version 1.0
 * @see Action
 */
public interface Property extends Valuable<Serializable>, LocaleViewable {}

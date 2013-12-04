package com.temple.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.temple.util.human.Habit;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public class HabitConverter implements Converter {

	private final Habit[] all = Habit.values();

	/**
	 * Constructor.
	 */
	public HabitConverter() {}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return this.all[Integer.valueOf(arg2).intValue()];
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return Integer.toString(((Habit) arg2).ordinal());
	}
}

package com.temple.web.converter;

import com.temple.util.human.Habit;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * TODOC
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public class HabitConverter implements Converter {

	private final Habit[] all = Habit.values();

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return this.all[Integer.parseInt(arg2)];
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return Integer.toString(((Habit) arg2).ordinal());
	}
}

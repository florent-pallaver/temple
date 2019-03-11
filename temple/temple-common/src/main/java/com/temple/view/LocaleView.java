package com.temple.view;

import com.temple.util.human.Language;


/**
 * Base interface to represent another way to view a {@link LocaleViewable locale viewable object}.
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public interface LocaleView extends View {

	/**
	 * @return the {@link Language} used to create this view.
	 */
	Language getUsedLanguage();
}

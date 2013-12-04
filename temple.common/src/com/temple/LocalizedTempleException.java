package com.temple;

import com.temple.util.human.Language;
import com.temple.view.LocaleStringViewHelper;
import com.temple.view.LocaleViewable;

/**
 * Wrapper Exception of a {@link LocaleViewableTempleException}
 * 
 * @author Florent Pallaver
 * @version 1.0
 */
public class LocalizedTempleException extends AbstractTempleException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * @param lv
	 * @param language - the language to use to create the localized messages of this exception.
	 * @param detailed
	 */
	public LocalizedTempleException(LocaleViewable lv, Language language, boolean detailed) {
		this(LocaleStringViewHelper.createLocaleString(lv, language, detailed));
	}

	/**
	 * Constructor.
	 * 
	 * @param cause - TODOC
	 * @param language - the language to use to create the localized messages of this exception.
	 */
	public LocalizedTempleException(LocaleViewableTempleException cause, Language language) {
		this(LocaleStringViewHelper.createLocaleString(cause, language, true), cause);
	}

	/**
	 * Constructor.
	 * 
	 * @param cause - TODOC
	 * @param language - the language to use to create the localized messages of this exception.
	 * @param detailed - <code>true</code> if a detailed message should be set, <code>false</code> otherwise.
	 */
	public LocalizedTempleException(LocaleViewableTempleException cause, Language language, boolean detailed) {
		this(LocaleStringViewHelper.createLocaleString(cause, language, detailed), cause);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	protected LocalizedTempleException(String message) {
		this(message, null);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 */
	protected LocalizedTempleException(String message, Throwable cause) {
		super(message, cause);
	}
}

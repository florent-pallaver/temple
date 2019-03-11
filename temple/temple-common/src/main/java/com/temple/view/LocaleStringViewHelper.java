package com.temple.view;

import com.temple.Module;
import com.temple.util.human.Language;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;

/**
 * Helper class for {@link LocaleView}s.
 *
 * @author Florent Pallaver
 * @version 1.0
 * @see #createLocaleString(LocaleViewable, SupportedLanguage, boolean)
 */
public final class LocaleStringViewHelper {

	private LocaleStringViewHelper() {}

	/**
	 * Creates a localized String for the given {@link SupportedLanguage} using the {@link Module#bundleBaseName the
	 * module
	 * default locale bundle} of the given {@link LocaleViewable}.
	 *
	 * @param lv - a {@link LocaleViewable}.
	 * @param l - a {@link SupportedLanguage}.
	 * @param detailed - <code>true</code> if the detailed localized String is wanted, <code>false</code> otherwise.
	 * @return a localized String.
	 * @throws MissingResourceException if no localized String can be found for the given {@link LocaleViewable}.
	 */
	public static final String createLocaleString(LocaleViewable lv, Language l, final boolean detailed) throws MissingResourceException {
		String localizedString = null;
		if (lv != null) {
			final StringBuilder localeKey = new StringBuilder(lv.getLocaleKey());
			if (detailed) {
				localeKey.append(LocaleStringView.DETAIL_KEY_SUFFIX);
			}
			final String lk = localeKey.toString();
			try {
				final ResourceBundle rb = LocaleStringViewHelper.findResourceBundle(lv.getBundle(), l.getLocale());
				final boolean containsKey = rb.containsKey(lk);
				localizedString = containsKey ? rb.getString(lk) : lk;
				final Serializable[] localeParameters = lv.getLocaleParameters();
				if (localeParameters.length > 0) {
					final Object[] localizedStringParameters = new Object[localeParameters.length];
					for (int i = localeParameters.length; i-- > 0;) {
						final Object lp = localeParameters[i];
						if (lp == null || lp instanceof LocaleViewable) {
							localizedStringParameters[i] = LocaleStringViewHelper.createLocaleString((LocaleViewable) lp, l, detailed);
						} else if (lp instanceof LocaleStringView) {
							final LocaleStringView slv = (LocaleStringView) lp;
							localizedStringParameters[i] = detailed ? slv.getDetailedString() : slv.getString();
						} else {
							localizedStringParameters[i] = lp.toString();
						}
					}
					localizedString = containsKey ? MessageFormat.format(localizedString, localizedStringParameters) : localizedString
							+ Arrays.toString(localizedStringParameters);
				}
			} catch (final Exception e) {
				Module.DEFAULT.logger.log(Level.WARNING, "Unable to find key : '" + lk + "' in " + lv.getBundle().getBaseName());
				localizedString = lk;
			}
		}
		return localizedString;
	}

	private static final ResourceBundle findResourceBundle(LocaleBundle m, Locale l) throws MissingResourceException {
		ResourceBundle rb = null;
		// getBundle() caches the returned result.
		try {
			rb = ResourceBundle.getBundle(m.getBaseName(), l);
		} catch (final MissingResourceException e) {
			if (Module.DEFAULT.logger.isLoggable(Level.WARNING)) {
				Module.DEFAULT.logger.log(Level.WARNING, "The bundle of the module {0} cannot be found, the default module will be used.", m);
				Module.DEFAULT.logger.warning(e.getMessage());
			}
			rb = ResourceBundle.getBundle(Module.DEFAULT.bundleBaseName, l);
		}
		return rb;
	}
}

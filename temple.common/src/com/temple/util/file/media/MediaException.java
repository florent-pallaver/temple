package com.temple.util.file.media;

import com.temple.LocaleViewableTempleException;
import com.temple.Module;
import com.temple.view.LocaleViewable;
import java.io.Serializable;

/**
 * TODOC
 *
 * @author Florent Pallaver
 * @version 1.0
 */
public abstract class MediaException extends LocaleViewableTempleException {

	private static final long serialVersionUID = 1L;

	protected MediaException(LocaleViewable lv, Throwable cause) {
		super(lv, cause);
	}

	protected MediaException(Module module, Throwable cause) {
		super(module, cause);
	}

	protected MediaException(Serializable[] parameters, Module module, Throwable cause) {
		super(parameters, module, cause);
	}

	protected MediaException(Serializable[] parameters, Module module) {
		super(parameters, module);
	}

	protected MediaException(String keySuffix, Serializable[] parameters, Module module, Throwable cause) {
		super(keySuffix, parameters, module, cause);
	}

	protected MediaException(String keySuffix, Serializable[] parameters, Module module) {
		super(keySuffix, parameters, module);
	}
}

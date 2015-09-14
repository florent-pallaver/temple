package com.temple.service.cdi.util.file.file;

import com.temple.service.cdi.AbstractCDIBean;
import com.temple.service.cdi.ImplementationStrategy;
import com.temple.service.cdi.OperatingSystem;
import com.temple.util.file.MimeTypeHelper;
import java.io.File;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author flominou
 * @version 1.0
 */
@ApplicationScoped
@OperatingSystem(OperatingSystem.UNIX)
@ImplementationStrategy(ImplementationStrategy.FILE)
public class UnixMimeTypeHelper extends AbstractCDIBean implements MimeTypeHelper {
	
	@Override
	public String findMimeType(File f) {
		return null ;
	}
	
}

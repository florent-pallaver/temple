package com.temple.web.component;

import java.io.IOException;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author flominou
 */
@FacesComponent(createTag = true)
public class GeoTree extends UIComponentBase {

	@Override
	public String getFamily() {
		return "com.temple.web.component";
	}

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		final ResponseWriter rw = context.getResponseWriter();
	}
	
	
}

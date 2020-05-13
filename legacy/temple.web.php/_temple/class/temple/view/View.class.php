<?php

namespace temple\view;

/**
 * Base contract for an object to be used as a view.
 * <br>
 * A view is basically what is displayed on a web page.
 *
 * @author florent
 */
interface View {

	/**
	 * @return Renderable
	 */
	function toRenderable() ;
	
}

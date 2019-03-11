<?php

namespace temple\web\html ;

/**
 * TODOC
 * 
 * @author Florent
 */
interface HTMLElement extends \temple\view\Renderable {

	/**
	 * @return a string representation of this object.
	 */
	function toString() ;

	/**
	 * 
	 * @param \temple\web\html\RenderObserver $ro
	 */
	function addRenderListener(RenderObserver $ro) ;
	
//	function removeRenderListener(RenderObserver $ro) ;
	
}

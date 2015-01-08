<?php

namespace temple\web\html\bootstrap;

/**
 *
 * @author florent
 */
interface Component extends \temple\web\html\HTMLElement {
	
	const JQUERYFIELD_CSS_CLASS = '_jqueryField' ;
	
	/**
	 * @return Component
	 */
//	function setJQueryField() ;
	
	/**
	 * @param mixed $help
	 * @return Component
	 */
	function setHelp($help) ;

}

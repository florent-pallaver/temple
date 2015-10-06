<?php

namespace temple\web\html\bootstrap;

/**
 *
 * @author florent
 */
interface Component extends \temple\web\html\FrameworkComponent {
	
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

	/**
	 * @return string
	 */
	function getTitle() ;
	
	/**
	 * @param string $title
	 * @return Component
	 */
	function setTitle($title) ;

	
}

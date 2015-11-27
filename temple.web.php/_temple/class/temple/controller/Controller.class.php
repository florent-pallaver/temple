<?php

namespace temple\controller;

/**
 * TODOC 
 * 
 * @author florent
 */
interface Controller extends \temple\util\Nameable, \temple\util\Iconable {

	/**
	 * @return array
	 */
	function getLocales() ;
	
    /**
	 * @return string the message to send the user when this action fails
	 */
	function getFailureMessage() ;
	
	/**
	 * TODOC
	 * @return \temple\view\Renderable the response to render, null if none should be rendered for whatever reason.
	 */
	function createResponse() ;
	
}

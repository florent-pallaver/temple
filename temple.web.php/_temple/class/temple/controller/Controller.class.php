<?php

namespace temple\controller;

/**
 * TODOC 
 * 
 * @author florent
 */
interface Controller {

	/**
	 * @return string 
	 */
	function getActionDescription() ;
	
	/**
	 * TODOC
	 * @return \temple\view\Renderable the response to render, null if none should be rendered for whatever reason.
	 */
	function createResponse() ;
	
}

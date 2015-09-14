<?php

namespace temple\web\html;

/**
 *
 * @author florent
 */
interface HTMLDocument extends \temple\view\Renderable {
	
	const CHARSET_UTF_8 = 'UTF-8' ;
	
	/**
	 * @return HTMLNode
	 */
	function getTitle() ;
	
	/**
	 * @return HTMLNode
	 */
	function getHead() ;
	
	/**
	 * @return HTMLNode
	 */
	function getBody() ;

}

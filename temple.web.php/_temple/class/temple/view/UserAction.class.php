<?php

namespace temple\view;

/**
 * Description of UserAction
 *
 * @author flominou
 */
interface UserAction extends \temple\util\Labelable {

	/**
	 * @return string the controller's fully qualified class name
	 */
	function getControllerClass() ;
	
	/**
	 * @return array
	 */
	function getFieldsLocale() ;
}

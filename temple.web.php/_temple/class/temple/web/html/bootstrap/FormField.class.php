<?php

namespace temple\web\html\bootstrap;

/**
 *
 * @author florent
 */
interface FormField extends FormComponent, \temple\util\Nameable {

	/**
	 * @param string $value the value to set
	 * @return FormField this object
	 */
	function setValue($value) ;

	/**
	 * @return boolean TODOC
	 */
	function isRequired() ;
	
	/**
	 * 
	 * @param boolean $disabled
	 * @return FormField this object
	 */
	function setDisabled($disabled = true) ;
	
}

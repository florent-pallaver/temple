<?php

namespace temple\web\html\bootstrap;

/**
 *
 * @author florent
 */
interface FormField extends FormComponent {

	/**
	 * @param string $value the value to set
	 * @return FormField this object
	 */
	function setValue($value) ;

}

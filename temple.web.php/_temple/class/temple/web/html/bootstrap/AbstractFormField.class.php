<?php

namespace temple\web\html\bootstrap;

/**
 * Description of AbstractFormField
 *
 * @author florent
 */
abstract class AbstractFormField extends AbstractComponent implements FormField {
	
	public function setForm($formId) {
		$this->setAttribute('form', $formId) ;
		return $this ;
	}
	
	public function isRequired() {
		return (boolean)$this->getAttribute('required');
	}
	
}

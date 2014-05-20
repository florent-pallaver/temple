<?php

namespace temple\web\html\bootstrap;

/**
 * Description of FormGroup
 *
 * @author florent
 */
class FormGroup extends AbstractGroup {

	private static $fieldKey = '_field|' ;
	
	public function __construct(FormField $field, $label, $srOnly = true, $cssClass = null) {
		parent::__construct('form', null, $cssClass);
		$this->addChild(ComponentFactory::createLabel($label, $srOnly ? 'sr-only' : null))
				->addChild($field, self::$fieldKey) ;
	}
	

}

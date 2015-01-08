<?php

namespace temple\web\html\bootstrap;

/**
 * Description of FormGroup
 *
 * @author florent
 */
class FormGroup extends AbstractGroup {

	private static $fieldKey = '_field|' ;
	
	private static $labelKey = '_label|' ;
	
	public function __construct(FormField $field, $label, $srOnly = true, $cssClass = null) {
		parent::__construct('form', null, $cssClass);
		$this->addChild(ComponentFactory::createLabel($label, 'control-label')->addCssClass($srOnly ? 'sr-only' : null), self::$labelKey)
				->addChild(ComponentFactory::createComponent('div')->addChild($field), self::$fieldKey) ;
	}
	
	/**
	 * @return \temple\web\html\HTMLNode
	 */
	public function getLabel() {
		return $this->getChild(self::$labelKey) ;
	}

	/**
	 * 
	 * @return \temple\web\html\HTMLNode
	 */
	public function getFieldDiv() {
		return $this->getChild(self::$fieldKey) ;
	}
	
}

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
	
	public static $defaultLabelCssClass = null ;
	
	public static $defaultFieldCssClass = null ;
	
	/**
	 * 
	 * @param \temple\web\html\bootstrap\FormField $field
	 * @param type $label
	 * @param type $srOnly deprecated !
	 * @param type $cssClass
	 */
	public function __construct(FormField $field, $label, $srOnly = true, $cssClass = null) {
		parent::__construct('form', null, $cssClass);
		$this->addAllChildren([
			self::$labelKey => $this->createHTMLNode('label', self::$defaultLabelCssClass, new \temple\web\html\HTMLString($label, true))->addCssClass('control-label'),
			self::$fieldKey => $this->createHTMLNode('div', self::$defaultFieldCssClass, $field)
		], true) ;
		if($field->isRequired()) {
			$this->getLabel()->addCssClass('required');
		}
	}
	
	/**
	 * @return \temple\web\html\Node
	 */
	public function getLabel() {
		return $this->getChild(self::$labelKey) ;
	}

	/**
	 * 
	 * @return FormField
	 */
	public function getFieldDiv() {
		return $this->getChild(self::$fieldKey) ;
	}

	/**
	 * @param string $label
	 * @param string $field
	 */
	public static function setDefaultCssClasses($label = null, $field = null) {
		self::$defaultLabelCssClass = $label ;
		self::$defaultFieldCssClass = $field ;
	}
	
	/**
	 * 
	 * @param \temple\web\html\bootstrap\FormField $input
	 * @return \temple\web\html\bootstrap\FormGroup
	 */
	public static function create(FormField $input) {
		return new FormGroup($input, $input->getTitle()) ;
	}
	
}

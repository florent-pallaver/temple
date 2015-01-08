<?php

namespace temple\web\html\bootstrap;

/**
 * Description of LabeledCheckbox
 *
 * @author florent
 */
class LabeledCheckbox extends AbstractFormField {

	const TYPE_HIDDEN = 'hidden';
	const TYPE_TEXT = 'text';
	const TYPE_SECRET = 'password';
	const TYPE_RADIO = 'radio';
	const TYPE_CHECKBOX = 'checkbox';
	const TYPE_FILE = 'file';
	const TYPE_EMAIL = 'email';
	const TYPE_NUMBER = 'number';

	/**
	 * @var Input
	 */
	private $checkbox ;
	
	/**
	 * 
	 * @param type $label
	 * @param type $name
	 * @param type $value
	 * @param type $before
	 * @param type $cssClass
	 */
	public function __construct($label, $name, $value, $before = true, $cssClass = null) {
		parent::__construct('label', $cssClass);
		$this->checkbox = Input::createCheckbox($name, $value) ;
		if($before) {
			$this->addChild($this->checkbox) ;
		}
		$this->addChild(ComponentFactory::toHTMLElement($label)) ;
		if(!$before) {
			$this->addChild($this->checkbox) ;
		}
	}
	
	/**
	 * @return Input
	 */
	public function getCheckbox() {
		return $this->checkbox ;
	}
	
	public function setValue($value) {
		$this->checkbox->setAttribute('checked', $value ? 'checked' : null);
		return $this;
	}

	/**
	 * 
	 * @param type $label
	 * @param type $name
	 * @param type $value
	 * @param type $before
	 * @param type $cssClass
	 * @return LabeledCheckbox
	 */
	public static function create($label, $name, $value, $before = true, $cssClass = null) {
		return new LabeledCheckbox($label, $name, $value, $before, $cssClass) ;
	}
	
	
}

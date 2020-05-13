<?php

namespace temple\web\html\bootstrap;

/**
 * Description of LabeledCheckbox
 *
 * @author florent
 */
class LabeledCheckbox extends AbstractFormField {

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
	
	public function isRequired() {
		return $this->checkbox->isRequired() ;
	}

	public function setDisabled($disabled = true) {
		$this->checkbox->setDisabled($disabled);
		return $this ;
	}

	public function setForm($formId) {
		$this->checkbox->setForm($formId);
		return $this ;
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
	public static function create($label, $name, $value = 1, $before = true, $cssClass = null) {
		return new LabeledCheckbox($label, $name, $value, $before, $cssClass) ;
	}
	
}

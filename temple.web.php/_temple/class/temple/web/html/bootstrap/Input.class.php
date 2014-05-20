<?php

namespace temple\web\html\bootstrap;

/**
 * Description of Input
 *
 * @author florent
 */
class Input extends AbstractFormField {

	const TYPE_HIDDEN = 'hidden';
	const TYPE_TEXT = 'text';
	const TYPE_SECRET = 'password';
	const TYPE_EMAIL = 'email';
	const TYPE_RADIO = 'radio';
	const TYPE_CHECKBOX = 'checkbox';

	public function __construct($name, $type, $placeholder, $required = false, $cssClass = null) {
		parent::__construct('input', $cssClass);
		$this->addCssClass('form-control')
				->setName($name)
				->setAttribute('type', $type)
				->setAttribute('placeholder', $placeholder)
				->setRequired($required);
	}

	public function setValue($value) {
		$this->setAttribute('value', $value);
		return $this;
	}

	/**
	 * @param boolean $required
	 * @return Input
	 */
	public function setRequired($required) {
		return $this->setAttribute('required', $required ? 'required' : null);
	}

	/**
	 * @param int $max
	 * @return Input
	 */
	public function setMaxLength($max) {
		return $this->setAttribute('maxlength', $max > 0 ? $max : null);
	}

	/**
	 * 
	 * @param type $name
	 * @param type $placeholder
	 * @param type $required
	 * @param type $cssClass
	 * @return \temple\web\html\bootstrap\Input
	 */
	public static function create($name, $placeholder, $required = false, $cssClass = null) {
		return new Input($name, self::TYPE_TEXT, $placeholder, $required, $cssClass);
	}

	/**
	 * 
	 * @param type $name
	 * @param type $placeholder
	 * @param type $required
	 * @param type $cssClass
	 * @return \temple\web\html\bootstrap\Input
	 */
	public static function createSecret($name, $placeholder, $required = false, $cssClass = null) {
		return new Input($name, self::TYPE_SECRET, $placeholder, $required, $cssClass);
	}

	/**
	 * 
	 * @param type $name
	 * @return Input
	 */
	public static function createHidden($name) {
		return new Input($name, self::TYPE_HIDDEN, null, false);
	}

	public static function createRadio($name, $required = false, $cssClass = null) {
		
	}

	/**
	 * 
	 * @param type $name
	 * @return Input
	 */
	public static function createCheckbox($name, $value) {
		$i = new Input($name, self::TYPE_CHECKBOX, null, false) ;
		return $i->setAttribute('class', null)->setAttribute('value', $value) ;
	}

}

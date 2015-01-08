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
	const TYPE_RADIO = 'radio';
	const TYPE_CHECKBOX = 'checkbox';
	const TYPE_FILE = 'file';
	const TYPE_EMAIL = 'email';
	const TYPE_NUMBER = 'number';

	const ACCEPT_ALL = '*/*' ;
	const ACCEPT_VIDEO = 'video/*' ;
	const ACCEPT_IMAGE = 'image/*' ;
	const ACCEPT_AUDIO = 'audio/*' ;
	
	public function __construct($name, $type, $placeholder, $required = false, $cssClass = 'form-control') {
		parent::__construct('input', $cssClass);
		$this->setName($name)
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
	public static function create($name, $placeholder, $required = false, $cssClass = 'form-control') {
		return new Input($name, self::TYPE_TEXT, $placeholder, $required, $cssClass);
	}

	/**
	 * 
	 * @param type $name
	 * @param type $required
	 * @param type $multiple
	 * @param type $accept
	 * @return Input
	 */
	public static function createFile($name, $required = false, $multiple = false, $accept = self::ACCEPT_ALL) {
		$i = new Input($name, self::TYPE_FILE, null, $required) ;
		if($multiple) {
			$i->setAttribute('multiple', 'multiple') ;
		}
		return $i->setAttribute('accept', $accept) ;
	}
	
	/**
	 * 
	 * @param type $name
	 * @param type $placeholder
	 * @param type $required
	 * @param type $cssClass
	 * @return \temple\web\html\bootstrap\Input
	 */
	public static function createNumber($name, $placeholder, $required = false, $cssClass = 'form-control') {
		return new Input($name, self::TYPE_NUMBER, $placeholder, $required, $cssClass);
	}

		/**
	 * 
	 * @param type $name
	 * @param type $placeholder
	 * @param type $required
	 * @param type $cssClass
	 * @return \temple\web\html\bootstrap\Input
	 */
	public static function createEmail($name, $placeholder, $required = false, $cssClass = 'form-control') {
		return new Input($name, self::TYPE_EMAIL, $placeholder, $required, $cssClass);
	}

	/**
	 * 
	 * @param type $name
	 * @param type $placeholder
	 * @param type $required
	 * @param type $cssClass
	 * @return \temple\web\html\bootstrap\Input
	 */
	public static function createSecret($name, $placeholder, $required = false, $cssClass = 'form-control') {
		return new Input($name, self::TYPE_SECRET, $placeholder, $required, $cssClass);
	}

	/**
	 * 
	 * @param type $name
	 * @return Input
	 */
	public static function createHidden($name, $value = null) {
		$i = new Input($name, self::TYPE_HIDDEN, null, false, null);
		return $i->setValue($value) ;
	}

	/**
	 * 
	 * @param type $name
	 * @return Input
	 */
	public static function createCheckbox($name, $value) {
		$i = new Input($name, self::TYPE_CHECKBOX, null, false, null) ;
		return $i->setAttribute('value', $value) ;
	}

}

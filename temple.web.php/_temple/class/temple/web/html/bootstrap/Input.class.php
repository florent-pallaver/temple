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
	const TYPE_RANGE = 'range';
	const TYPE_SEARCH = 'search';
	const TYPE_TELEPHONE = 'tel';

	const ACCEPT_ALL = '*/*' ;
	const ACCEPT_VIDEO = 'video/*' ;
	const ACCEPT_IMAGE = 'image/*' ;
	const ACCEPT_AUDIO = 'audio/*' ;
	const ACCEPT_PDF = 'application/pdf' ;
	
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
	 * 
	 * @param mixed $ac TODOC
	 * @return Input
	 */
	public function setAutoComplete($ac = false) {
		return $this->setAttribute('autocomplete', is_string($ac) ? $ac : ($ac === null ? null : ($ac ? 'on' : 'off'))) ;
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
	 * @param array|string $accept
	 * @return Input
	 */
	public static function createFile($name, $required = false, $multiple = false, $accept = self::ACCEPT_ALL) {
		$i = new Input($name, self::TYPE_FILE, null, $required) ;
		if($multiple) {
			$i->setAttribute('multiple', 'multiple') ;
		}
		return $i->setAttribute('accept', is_array($accept) ? implode(',', $accept) : $accept) ;
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
	public static function createTel($name, $placeholder, $required = false, $cssClass = 'form-control') {
		return new Input($name, self::TYPE_TELEPHONE, $placeholder, $required, $cssClass);
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
	public static function createSearch($name, $placeholder, $required = true, $cssClass = 'form-control') {
		return new Input($name, self::TYPE_SEARCH, $placeholder, $required, $cssClass);
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

	/**
	 * 
	 * @param type $name
	 * @param type $max
	 * @param type $min
	 * @param type $step
	 * @param type $value
	 * @return type
	 */
	public static function createRange($name, $max = 100, $min = 0, $step = 1, $value = null) {
		$i = new Input($name, self::TYPE_RANGE, null, true, null) ;
		return $i->setValue($value)
				->setAttributes(['max'=>$max, 'min'=>$min, 'step'=>$step]) ;
	}
	
}

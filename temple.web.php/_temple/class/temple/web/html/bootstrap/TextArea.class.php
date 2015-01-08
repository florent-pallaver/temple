<?php

namespace temple\web\html\bootstrap;

/**
 * Description of TextArea
 *
 * @author florent
 */
class TextArea extends AbstractFormField {

	const DEFAULT_ROWS_COUNT = 6 ;
	
	public function __construct($name, $placeholder, $rowCount = self::DEFAULT_ROWS_COUNT, $required = false, $cssClass = 'form-control') {
		parent::__construct('textarea', $cssClass);
		$this->setName($name)
				->setAttribute('placeholder', $placeholder)
				->setAttribute('rows', $rowCount)
				->setRequired($required);
	}

	public function setValue($value) {
		$this->addChild(new \temple\web\html\HTMLString($value, false), 'value') ;
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
	public static function create($name, $placeholder, $required = false, $rowCount = self::DEFAULT_ROWS_COUNT, $cssClass = 'form-control') {
		return new TextArea($name, $placeholder, $rowCount, $required, $cssClass);
	}

}

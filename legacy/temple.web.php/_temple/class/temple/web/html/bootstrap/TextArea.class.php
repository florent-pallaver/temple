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
				->setTitle($placeholder)
				->setAttribute('placeholder', $placeholder)
				->setAttribute('rows', $rowCount)
				->setRequired($required);
	}

	/**
	 * 
	 * @param boolean $readOnly
	 * @return \temple\web\html\bootstrap\TextArea
	 */
	public function setReadOnly($readOnly = true) {
		$this->setAttribute('readonly', $readOnly ? 'readonly' : null) ;
		return $this ;
	}
	
	public function setValue($value) {
		$this->addChild(new \temple\web\html\HTMLString($value, true), 'value') ;
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
	 * @param string $name
	 * @param string $placeholder
	 * @param boolean $required
	 * @param int $rowCount
	 * @param string  $cssClass
	 * @return TextArea
	 */
	public static function create($name, $placeholder, $required = false, $rowCount = self::DEFAULT_ROWS_COUNT, $cssClass = 'form-control') {
		return new TextArea($name, $placeholder, $rowCount, $required, $cssClass);
	}

}

<?php

namespace temple\web\html\bootstrap;


/**
 * Description of SelectManyCheckbox
 *
 * @author florent
 */
class CheckboxesGroup extends AbstractFormField {

	private static $defaultCssVariant ;
		
	private $name;
	private $required;
	private $checkboxes;
	private $checkboxesCssClass;

	public function __construct($name, $cssClass = null, $checkboxesCssClass = null, $required = false) {
		parent::__construct('div', $cssClass);
		$this->name = $name . '[]';
		$this->required = $required;
		$this->checkboxes = [];
		$this->checkboxesCssClass = $checkboxesCssClass;
	}

	/**
	 * 
	 * @param mixed $value scalar or array of scalars
	 * @return CheckboxesGroup
	 */
	public function setValue($value) {
		$values = self::enumsToInts($value) ;
		foreach($this->checkboxes as $v => $cb) {
			$cb->setAttribute('checked', in_array($v, $values, true) ? 'checked' : null) ;
		}
		return $this;
	}

	public function isRequired() {
		return $this->required;
	}

	/**
	 * 
	 * @return array an array containing all the checkboxes inputs within this components
	 */
	public function getCheckboxes() {
		return $this->checkboxes;
	}

	public function addOption($value, $label, $labelCssClass = null) {
		$r = Input::createCheckbox($this->name, $value)
				->addCssClass($this->checkboxesCssClass)
				->setAttribute('autocomplete', 'off') ;
		$this->checkboxes[$value] = $r ;
		return $this->addChild($this->createHTMLNode('label', $labelCssClass, [$r, $label]), $value) ;
	}

	public function addButtonOption($value, $label, $labelCssClass = null, CssVariant $labelCssVariant = null) {
		$lcv = _dif($labelCssVariant, self::$defaultCssVariant) ;
		return $this->addOption($value, $label, $labelCssClass . ' ' . $lcv->compose('btn')) ;
	}
	
	/**
	 * 
	 * @param type $name
	 * @param array $options
	 * @param type $cssClass
	 * @return \temple\web\html\bootstrap\SelectOneRadio
	 */
	public static function create($name, array $options, $cssClass = null) {
		$sor = new ChekboxesGroup($name, $cssClass);
		foreach ($options as $value => $label) {
			$sor->addOption($value, $label);
		}
		return $sor;
	}

	/**
	 * 
	 * @param type $name
	 * @param array $options
	 * @param array $values array of scalar (typically integer)
	 * @param string $checkboxesCssClass
	 * @param array $optionVariants a CssVariant array
	 * @return SelectOneRadio
	 */
	public static function createButtonGroup($name, array $options, array $values = [], $checkboxesCssClass = null, array $optionVariants = []) {
		$sor = new CheckboxesGroup($name, 'btn-group', $checkboxesCssClass);
		$ints = self::enumsToInts($values) ;
		foreach ($options as $v => $label) {
			$sor->addButtonOption($v, $label, (in_array($v, $ints) ? 'active' : NULL), _iod($optionVariants, $v));
		}
		return $sor->setValue($ints)->setData(['toggle' => 'buttons']);
	}

	/**
	 * 
	 * @param \temple\web\html\bootstrap\CssVariant $variant
	 */
	public static function setDefaultCssVariant(CssVariant $variant) {
		self::$defaultCssVariant = $variant ;
	}
	
	/**
	 * 
	 * @param array $enums
	 * @return array
	 */
	private static function &enumsToInts(array $enums) {
		$ints = [] ;
		foreach($enums as $v) {
			$ints[] = _eti($v) ;
		}
		return $ints ;
	}
	
	private static function _init() {
		self::$defaultCssVariant = CssVariant::$PRIMARY ;
	}
	
	public function setForm($formId) {
		foreach($this->checkboxes as $c) {
			$c->setForm($formId) ;
		}
	}
	
}

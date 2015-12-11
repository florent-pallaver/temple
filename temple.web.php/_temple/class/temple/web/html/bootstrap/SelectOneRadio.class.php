<?php

namespace temple\web\html\bootstrap;

/**
 * Description of SelectOneRadio
 *
 * @author florent
 */
class SelectOneRadio extends AbstractFormField {

	private static $defaultCssVariant ;
	
	private $name;
	private $required;
	private $radios;
	private $radioCssClass;

	public function __construct($name, $cssClass = null, $radioCssClass = null, $required = true) {
		parent::__construct('div', $cssClass);
		$this->name = $name;
		$this->required = $required;
		$this->radios = [];
		$this->radioCssClass = $radioCssClass;
	}

	public function getName() {
		return $this->name;
	}
		
	/**
	 * 
	 * @param type $value
	 * @return \temple\web\html\bootstrap\SelectOneRadio
	 */
	public function setValue($value) {
		$v = _eti($value) ;
		foreach ($this->radios as $radio) {
			if ($radio->getAttribute('value') == $v) {
				$radio->setAttribute('checked', 'checked') ;
			}
		}
		return $this;
	}

	public function setForm($formId) {
		foreach($this->radios as $c) {
			$c->setForm($formId) ;
		}
		return $this ;
	}

	public function setDisabled($disabled = true) {
		foreach($this->radios as $c) {
			$c->setDisabled($disabled) ;
		}
		return $this ;
	}
	
	public function isRequired() {
		return $this->required;
	}
	
	/**
	 * 
	 * @return array an array containing all the radio inputs within this components
	 */
	public function getRadios() {
		return $this->radios;
	}

	public function addOption($value, $label, $labelCssClass = null) {
		$r = new Input($this->name, Input::TYPE_RADIO, null, $this->required, $this->radioCssClass);
		$l = ComponentFactory::createComponent('label', $labelCssClass, $r)
			->addChild(ComponentFactory::toHTMLElement($label));
		$this->radios[] = $r->setValue($value)->setAttribute('autocomplete', 'off');
		return $this->addChild($l);
	}

	public function addButtonOption($value, $label, $labelCssClass = null, CssVariant $labelCssVariant = null) {
		$lcv = _dif($labelCssVariant, self::$defaultCssVariant) ;
		return $this->addOption($value, $label, $labelCssClass . ' ' . $lcv->compose('btn')) ;
	}
	
	/**
	 * 
	 * @param type $name
	 * @param array $options
	 * @param type $required
	 * @return \temple\web\html\bootstrap\SelectOneRadio
	 */
	public static function create($name, array $options, $cssClass = null) {
		$sor = new SelectOneRadio($name, $cssClass);
		foreach ($options as $value => $label) {
			$sor->addOption($value, $label);
		}
		return $sor;
	}

	/**
	 * 
	 * @param type $name
	 * @param array $options
	 * @param mixed $val Enumeration or int
	 * @param string $radioCssClass
	 * @param array $optionVariants a CssVariant array
	 * @param boolean $required
	 * @return SelectOneRadio
	 */
	public static function createButtonGroup($name, array $options, $val = null, $radioCssClass = null, array $optionVariants = [], 
			$required = true) {
		$sor = new SelectOneRadio($name, 'btn-group', $radioCssClass, $required);
		$value = _eti($val) ;
		foreach ($options as $v => $label) {
			$rv = $label instanceof \temple\Enumeration ? $label->ordinal() : $v ;
			$sor->addButtonOption($rv, $label, ($value !== null && $rv == $value ? 'active' : NULL), _iod($optionVariants, $v));
		}
		return $sor->setValue($value)->setRole('group')->setData(['toggle' => 'buttons']);
	}

	/**
	 * 
	 * @param string $name
	 * @param boolean $val
	 * @param string $radioCssClass
	 * @param array $optionVariants
	 * @return SelectOneRadio
	 */
	public static function createYesNoGroup($name, $val = false, $radioCssClass = null, array $optionVariants = []) {
		return self::createButtonGroup($name, [\temple\CommonLocale::NO, \temple\CommonLocale::YES], $val, $radioCssClass, $optionVariants, true) ;
	}
	
	/**
	 * 
	 * @param \temple\web\html\bootstrap\CssVariant $variant
	 */
	public static function setDefaultCssVariant(CssVariant $variant) {
		self::$defaultCssVariant = $variant ;
	}
	
	private static function _init() {
		self::$defaultCssVariant = CssVariant::$PRIMARY ;
	}
	
}

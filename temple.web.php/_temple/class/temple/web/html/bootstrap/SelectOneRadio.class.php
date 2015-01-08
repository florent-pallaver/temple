<?php

namespace temple\web\html\bootstrap;

/**
 * Description of SelectOneRadio
 *
 * @author florent
 */
class SelectOneRadio extends AbstractFormField {

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

	/**
	 * 
	 * @param type $value
	 * @return \temple\web\html\bootstrap\SelectOneRadio
	 */
	public function setValue($value) {
		foreach ($this->radios as $radio) {
			if ($radio->getAttribute('value') == $value) {
				$radio->setAttribute('checked', 'checked') ;
			}
		}
		return $this;
	}

	public function isRequired() {
		return $this->required;
	}

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
		$lcv = _dif($labelCssVariant, CssVariant::$PRIMARY) ;
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
	 * @return SelectOneRadio
	 */
	public static function createButtonGroup($name, array $options, $value = null, $radioCssClass = null, array $optionVariants = []) {
		$sor = new SelectOneRadio($name, 'btn-group', $radioCssClass);
		foreach ($options as $v => $label) {
			$sor->addButtonOption($v, $label, 'before' . ($v == $value ? ' active' : ''), _iod($optionVariants, $v));
		}
		return $sor->setValue($value)->setData(['toggle' => 'buttons']);
	}

}

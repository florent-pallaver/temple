<?php

namespace temple\web\html\bootstrap;


/**
 * Description of SelectManyCheckbox
 *
 * @author florent
 */
class SelectManyCheckbox extends AbstractList implements FormField {

	/**
	 * @var CssVariant
	 */
	public static $DEFAULT_BUTTON_VARIANT ;
	
	private $name ;
	
	private $checkboxes ;
	
	public function __construct($name, $cssClass = null) {
		parent::__construct(false, '_checkboxes') ;
		$this->name = $name . '[]' ;
		$this->checkboxes = [] ;
		$this->addCssClass($cssClass) ;
	}
	
	public function addItem($item = null, $cssClass = null) {
		$k = $item->getValue() ;
		$i = Input::createCheckbox($this->name, $k) ;
		$this->addChild(new ListItem(ComponentFactory::createComponent('label', $cssClass, [$i, $item->getName()])));
		$this->checkboxes[$k] = $i ;
	}
	
	public function setForm($formId) {
		foreach($this->checkboxes as $c) {
			$c->setForm($formId) ;
		}
	}
	
	public function setValue($value) {
		foreach($value as $k => $v) {
			if(isset($this->checkboxes[$k])) {
				$this->checkboxes[$k]->setAttribute('checked', $v ? 'checked' : null) ;
			}
		}
		return $this ;
	}

	public function isRequired() {
		return false;
	}

	public function setDisabled($disabled = true) {
		foreach($this->checkboxes as $c) {
			$c->setDisabled($disabled) ;
		}
		return $this ;
	}

	/**
	 * 
	 * @param type $name
	 * @param array $options
	 * @param array $values
	 * @return SelectManyCheckbox
	 */
	public static function createButtonGroup($name, array $options, array $values = [], CssVariant $variant = null) {
		$smc = new SelectManyCheckbox($name, 'btn-group') ;
		
		$cv = _dif($variant, self::$DEFAULT_BUTTON_VARIANT) ;
		
		foreach($options as $k => $v) {
			$smc->addItem(new SelectManyCheckbox_Option($k, $v), $cv->compose('btn')) ;
		}
		
		$smc->unstyled()->setValue($values)->setData(['toggle'=>'buttons']) ;
		return $smc ;
	}

	private static function _init() {
		self::$DEFAULT_BUTTON_VARIANT = CssVariant::$DEFAULT ;
	}
	
}

final class SelectManyCheckbox_Option implements \temple\util\Nameable {
	
	private $value ;
	
	private $name ;

	public function __construct($value, $name) {
		$this->value = $value;
		$this->name = $name;
	}
	
	public function getValue() {
		return $this->value;
	}

	public function getName() {
		return $this->name;
	}
	
}

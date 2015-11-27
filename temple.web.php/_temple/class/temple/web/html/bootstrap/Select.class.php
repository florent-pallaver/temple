<?php

namespace temple\web\html\bootstrap;

/**
 * Description of Select
 *
 * @author florent
 */
class Select extends AbstractFormField {

	/**
	 * @var callable
	 */
	private $setValueCallback;

	/**
	 * @var array an array of string
	 */
	private $values;

	public function __construct($name, $cssClass = 'form-control') {
		parent::__construct('select', $cssClass);
		$this->setValueCallback = function($v) {
			$sv = _vts($v);
			$option = $this->getChild($sv);
			if ($option) {
				$option->setAttribute('selected', 'selected');
			}
			return $sv;
		};
		$this->values = [] ;
		$this->setName($name) ;
	}

	public function setValue($value) {
		$this->values = array_map($this->setValueCallback, _eia($value));
		return $this ;
	}

	/**
	 * @param boolean $required
	 * @return Select
	 */
	public function setRequired($required) {
		return $this->setAttribute('required', $required ? 'required' : null);
	}
	
	/**
	 * 
	 * @param boolean $m
	 * @return Select
	 */
	public function setMultiple($m = true) {
		return $this->setAttribute('multiple', $m ? 'multiple' : null);
	}

	/**
	 * 
	 * @param type $value
	 * @param type $label
	 * @return \temple\web\html\bootstrap\Select
	 */
	public function addOption($value, $label) {
		$v = strval($value);
		$o = new \temple\web\html\HTMLNode('option', [
			'value' => $v, 
			'selected' => in_array($v, $this->values) ? 'selected' : null
		]) ;
		return $this->addChild($o->addChild($this->toHTMLElement($label)), $v);
	}

	/**
	 * 
	 * @param string $label
	 * @return Select
	 */
	public function addNullOption($label = '') {
		return $this->addOption('', $label) ;
	}
	
	/**
	 * 
	 * @param type $name
	 * @param array $all
	 * @param type $noSelectionLabel
	 * @param type $multiple
	 * @return Select
	 */
	public static function createFromNameableModel($name, array $all, $noSelectionLabel = null, $multiple = false) {
		$s = new Select($name) ;
		if(!$multiple && $noSelectionLabel !== null) {
			$s->addNullOption($noSelectionLabel);
		} else {
			$s->setRequired(true) ;
		}
		foreach($all as $nm) {
			$s->addOption($nm->getId(), $nm->getName()) ;
		}
		return $s->setMultiple($multiple) ;
	}
	
	/**
	 * 
	 * @param type $name
	 * @param array $all
	 * @param type $noSelectionLabel
	 * @param type $multiple
	 * @return Select
	 */
	public static function createFromEnums($name, array $all, $noSelectionLabel = null, $multiple = false) {
		$s = new Select($name) ;
		if(!$multiple && $noSelectionLabel !== null) {
			$s->addNullOption($noSelectionLabel);
		} else {
			$s->setRequired(true) ;
		}
		foreach($all as $e) {
			$s->addOption($e->ordinal(), $e) ;
		}
		return $s->setMultiple($multiple) ;
	}
	
	private static function _init() {
		
	}

}

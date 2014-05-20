<?php

namespace temple\web\html\bootstrap;


/**
 * Description of SelectManyCheckbox
 *
 * @author florent
 */
class SelectManyCheckbox extends ItemList implements FormField {

	private $checkboxes ;
	
	public function __construct($name, array $options, array $value = []) {
		parent::__construct(false, '_checkboxes list-unstyled');
		$this->checkboxes = [] ;
		foreach($options as $k => $v) {
			$i = Input::createCheckbox($name . '[]', $k) ;
			$this->addItem(ComponentFactory::createComponent('label')
					->addChild($i)->addChild(InnerText::create($v)));
			$this->checkboxes[$k] = $i ;
		}
		$this->setValue($value) ;
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
	}
	
}

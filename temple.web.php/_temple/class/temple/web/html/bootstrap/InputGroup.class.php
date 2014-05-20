<?php

namespace temple\web\html\bootstrap;

/**
 * Description of InputGroup
 *
 * @author florent
 */
final class InputGroup extends AbstractGroup implements FormField {

	private static $pre = '_pre|';
	private static $inputKey = '_input|';
	private static $post = '_post|';

	public function __construct(Input $input, CssVariant $variant = null, $cssClass = null) {
		parent::__construct('input', $variant, $cssClass);
		$this->addChild(new \temple\web\html\HTMLElementList(), self::$pre)
				->addChild($input, self::$inputKey)
				->addChild(new \temple\web\html\HTMLElementList(), self::$post);
	}

	/**
	 * 
	 * @param type $icon
	 * @param type $pre
	 * @return \temple\web\html\bootstrap\InputGroup
	 */
	public function addIcon($icon, $pre = false) {
		$this->getElementList($pre)
				->addElement(ComponentFactory::createComponent('span', 'input-group-addon')
						->addChild(new Icon($icon)));
		return $this;
	}
	
	public function addButton(Button $button, $pre = false) {
		$this->getElementList($pre)
				->addElement(ComponentFactory::createComponent('div', 'input-group-btn')
						->addChild($button));
		return $this ;
	}
	
	public function addDropdown(Dropdown $dropdown, $pre = false) {
		$this->getElementList($pre)
				->addElement($dropdown->setAttribute('class', 'input-group-btn'));
		return $this ;
	}

	/**
	 * 
	 * @param type $pre
	 * @return \temple\web\html\HTMLElementList
	 */
	private function getElementList($pre) {
		return $this->getChild($pre ? self::$pre : self::$post);
	}

	public function setForm($formId) {
		$this->getChild(self::$inputKey)->setForm($formId) ;
		return $this ;
	}

	public function setValue($value) {
		$this->getChild(self::$inputKey)->setValue($value) ;
		return $this ;
	}
	
}

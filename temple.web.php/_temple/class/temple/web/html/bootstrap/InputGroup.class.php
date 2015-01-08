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
	 * @param \temple\web\html\bootstrap\InnerText $it
	 * @param type $pre
	 * @return \temple\web\html\bootstrap\InputGroup
	 */
	public function addInnerText(InnerText $it, $pre = false) {
		$this->getElementList($pre)
				->addElement(ComponentFactory::createComponent('span', 'input-group-addon')
						->addChild($it));
		return $this;
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
						->addChild(new InnerText($icon)));
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

	public function isRequired() {
		return $this->getChild(self::$inputKey)->isRequired() ;
	}
	
	/**
	 * 
	 * @param \temple\web\html\bootstrap\Input $input
	 * @param \temple\web\html\bootstrap\InnerText $it
	 * @param type $pre
	 * @param \temple\web\html\bootstrap\CssVariant $cv
	 * @param type $cssClass
	 * @return InputGroup
	 */
	public static function create(Input $input, InnerText $it, $pre = true, CssVariant $cv = null, $cssClass = null) {
		$ig = new InputGroup($input, $cv, $cssClass) ;
		return $ig->addInnerText($it, $pre) ;
	}
	
	/**
	 * 
	 * @param \temple\web\html\bootstrap\Input $input
	 * @param type $icon
	 * @param type $pre
	 * @param \temple\web\html\bootstrap\CssVariant $variant
	 * @param type $cssClass
	 * @return InputGroup
	 */
	public static function createIconed(Input $input, $icon, $pre = true, CssVariant $variant = null, $cssClass = null) {
		$ig = new InputGroup($input, $variant, $cssClass) ;
		return $ig->addIcon($icon, $pre) ;
	}
	
}

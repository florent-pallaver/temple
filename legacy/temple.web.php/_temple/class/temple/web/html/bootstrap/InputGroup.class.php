<?php

namespace temple\web\html\bootstrap;

// FIXME should extend Input and have a special render 
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
	 * @param mixed $it
	 * @param boolean $pre
	 * @return \temple\web\html\bootstrap\InputGroup
	 */
	public function addContent($it, $pre = false) {
		$this->getElementList($pre)
				->addElement(ComponentFactory::createComponent('span', 'input-group-addon', $this->toHTMLElement($it))
						);
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
				->addElement(ComponentFactory::createComponent('span', 'input-group-addon', $this->createIcon($icon, [\temple\web\html\fontawesome\IconVariation::$FW])));
		return $this;
	}
	
	/**
	 * 
	 * @param \temple\web\html\bootstrap\Button $button
	 * @param boolean $pre
	 * @return \temple\web\html\bootstrap\InputGroup
	 */
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
	 * @return Input
	 */
	public function getInput() {
		return $this->getChild(self::$inputKey) ;
	}
	
	/**
	 * 
	 * @param type $pre
	 * @return \temple\web\html\HTMLElementList
	 */
	private function getElementList($pre) {
		return $this->getChild($pre ? self::$pre : self::$post);
	}

	public function getName() {
		return $this->getInput()->getName() ;
	}
	
	public function setForm($formId) {
		$this->getInput()->setForm($formId) ;
		return $this ;
	}

	public function setValue($value) {
		$this->getInput()->setValue($value) ;
		return $this ;
	}

	public function isRequired() {
		return $this->getInput()->isRequired() ;
	}
	
	public function setDisabled($disabled = true) {
		$this->getInput()->setDisabled($disabled) ;
		return $this ;
	}
	
	/**
	 * 
	 * @param Input $input
	 * @param mixed $iconText
	 * @param boolean $pre
	 * @param CssVariant $cv
	 * @param string $cssClass
	 * @return InputGroup
	 */
	public static function create(Input $input, $iconText, $pre = true, CssVariant $cv = null, $cssClass = null) {
		$ig = new InputGroup($input, $cv, $cssClass) ;
		return $ig->addContent($iconText, $pre) ;
	}
	
	/**
	 * 
	 * @param Input $input
	 * @param string $icon
	 * @param boolean $pre
	 * @param CssVariant $variant
	 * @param string $cssClass
	 * @return InputGroup
	 */
	public static function createIconed(Input $input, $icon, $pre = true, CssVariant $variant = null, $cssClass = null) {
		$ig = new InputGroup($input, $variant, $cssClass) ;
		return $ig->addIcon($icon, $pre) ;
	}
	
}

<?php

namespace temple\web\html\bootstrap;

/**
 * Description of ButtonGroup
 *
 * @author florent
 */
class ButtonGroup extends AbstractGroup {

	public function __construct(CssVariant $variant = null, $cssClass = null) {
		parent::__construct('btn', $variant, $cssClass);
		$this->setRole('group') ;
		// FIXME set aria-label ...
	}
	
//	public function addButton(Button $button) {
//		$this->addChild($button) ;
//	}
	
	/**
	 * 
	 * @param type $label
	 * @param type $name
	 * @param type $value
	 * @param \temple\web\html\bootstrap\CssVariant $variant
	 * @param Input $checkbox
	 * @return ButtonGroup
	 */
//	public function addCheckboxButton($label, $name, $value = 1, CssVariant $variant = null, Input &$checkbox = null) {
//		$lc = new LabeledCheckbox($label, $name, $value) ;
//		$checkbox = $lc->addCssClass('before')->getCheckbox() ;
//		return $this->addChild($lc->addCompositeCssClass('btn', _dif($variant, CssVariant::$DEFAULT))) ;
//	}
	
	/**
	 * 
	 * @param array $buttons 
	 * @param \temple\web\html\bootstrap\CssVariant $variant
	 * @param string $cssClass
	 * @return \temple\web\html\bootstrap\ButtonGroup
	 */
	public static function create(array $buttons = [], CssVariant $variant = null, $cssClass = null) {
		$bg = new ButtonGroup($variant, $cssClass) ;
		$bg->addAllChildren($buttons) ;
		return $bg ;
	}
	
}

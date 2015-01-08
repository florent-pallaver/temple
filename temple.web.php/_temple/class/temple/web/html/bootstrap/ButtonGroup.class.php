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
	public function addCheckboxButton($label, $name, $value = 1, CssVariant $variant = null, Input &$checkbox = null) {
		$lc = new LabeledCheckbox($label, $name, $value) ;
		$checkbox = $lc->addCssClass('before')->getCheckbox() ;
		return $this->addChild($lc->addCompositeCssClass('btn', _dif($variant, CssVariant::$DEFAULT))) ;
	}
	
	/**
	 * 
	 * @param \temple\web\html\bootstrap\CssVariant $variant
	 * @param type $cssClass
	 * @return \temple\web\html\bootstrap\ButtonGroup
	 */
	public static function create(CssVariant $variant = null, $cssClass = null) {
		return new ButtonGroup($variant, $cssClass) ;
	}
	
	/**
	 * 
	 * @param CssVariant $variant
	 * @param type $cssClass
	 * @return ButtonGroup
	 */
	public static function createTogglable(CssVariant $variant = null, $cssClass = null) {
		return self::create($variant, $cssClass)->setData(['toggle'=>'buttons']) ;
	}
	
}

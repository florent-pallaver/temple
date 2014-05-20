<?php

namespace temple\web\html\bootstrap;

/**
 * Description of Button
 *
 * @author florent
 */
class Button extends AbstractFormField {

	const TYPE_BUTTON = 'button' ;
	
	const TYPE_SUBMIT = 'submit' ;
	
	const TYPE_RESET = 'reset' ;
	
	/**
	 * 
	 * @param type $icon
	 * @param type $text
	 * @param type $caret
	 * @param array $btnCssClass
	 */
	public function __construct($type, InnerText $innerText, CssVariant $variant = null) {
		parent::__construct('button') ;
		$variant_ = _dif($variant, CssVariant::$DEFAULT) ;
		$this->addCompositeCssClass('btn', $variant_)
				->addChild($innerText)
				->setAttribute('type', $type) ;
	}
	
	public function setValue($value) {
		$this->setAttribute('value', $value) ;
		return $this ;
	}
	
	/**
	 * 
	 * @param \temple\web\html\bootstrap\InnerText $innerText
	 * @param \temple\web\html\bootstrap\CssVariant $variant
	 * @return \temple\web\html\bootstrap\Button
	 */
	public static function create(InnerText $innerText, CssVariant $variant = null) {
		return new Button(self::TYPE_BUTTON, $innerText, $variant) ;
	}
	
	/**
	 * 
	 * @param \temple\web\html\bootstrap\InnerText $innerText
	 * @param \temple\web\html\bootstrap\CssVariant $variant if null default to CssVariant::$PRIMARY
	 * @return \temple\web\html\bootstrap\Button
	 */
	public static function createSubmit($name, InnerText $innerText, CssVariant $variant = null) {
		$b = new Button(self::TYPE_SUBMIT, $innerText, _dif($variant, CssVariant::$PRIMARY)) ;
		return $b->setAttribute('value', $name)->setName('action') ;
	}

	/**
	 * 
	 * @param \temple\web\html\bootstrap\InnerText $innerText
	 * @param \temple\web\html\bootstrap\CssVariant $variant
	 * @return Button
	 */
	public static function createReset(InnerText $innerText, CssVariant $variant = null) {
		$b = new Button(self::TYPE_RESET, $innerText, $variant) ;
		return $b->setName('reset') ;
	}

}

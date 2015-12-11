<?php

namespace temple\web\html\bootstrap;

/**
 * Description of Button
 *
 * @author florent
 */
class Button extends AbstractFormField {

    public static $SUBMIT_NAME = 'action';

    const TYPE_BUTTON = 'button';
    const TYPE_SUBMIT = 'submit';
    const TYPE_RESET = 'reset';

    /**
     * 
     * @param type $icon
     * @param type $text
     * @param type $caret
     * @param array $btnCssClass
     */
    public function __construct($type, $inline = null, CssVariant $variant = null) {
        parent::__construct('button');
        $variant_ = _dif($variant, CssVariant::$DEFAULT);
        $this->addCompositeCssClass('btn', $variant_)
                ->addChild($this->toHTMLElement($inline))
                ->setAttribute('type', $type)
                // work around for firefox persisting disabled state accross page loads (CF bootstrap doc)
                ->setAttribute('autocomplete', 'off');
    }

    public function setValue($value) {
        $this->setAttribute('value', $value);
        return $this;
    }

    /**
     * @return Button
     */
    public function setToConfirm() {
        return $this->addCssClass('_confirm');
    }

	/**
	 * @return Button
	 */
	public function setBlock() {
		$this->addCssClass('btn-block') ;
		return $this ;
	}
	
    /**
     * 
     * @param mixed $innerText
     * @param \temple\web\html\bootstrap\CssVariant $variant
     * @return \temple\web\html\bootstrap\Button
     */
    public static function create($innerText = null, CssVariant $variant = null) {
        return new Button(self::TYPE_BUTTON, $innerText, $variant);
    }

	/**
	 * 
	 * @param \temple\view\UserAction $a
	 * @param CssVariant $v
	 * @return Button
	 */
	public static function createSubmit(\temple\view\UserAction $a, CssVariant $v = null) {
		$b = new Button(self::TYPE_SUBMIT, self::createIconedLabel($a), self::variant($v)) ;
		return $b->setName(self::$SUBMIT_NAME)->setValue('submit') ;
	}
	
	/**
	 * 
	 * @param \temple\util\Iconable $i
	 * @param CssVariant $v
	 * @param string $value
	 * @return Button
	 */
	public static function createSubmit1(\temple\util\Iconable $i, CssVariant $v = null, $value = 'submit') {
		$b = new Button(self::TYPE_SUBMIT, self::createIconedLabel($i), self::variant($v));
		return $b->setName(self::$SUBMIT_NAME)->setValue($value) ;
	}
	
	/**
	 * 
	 * @param type $icon
	 * @param type $text
	 * @param \temple\web\html\bootstrap\CssVariant $variant
	 * @param type $value
	 * @return Button
	 */
    public static function createSubmit2($icon, $text, CssVariant $variant = null, $value = 'submit') {
        $b = new Button(self::TYPE_SUBMIT, \temple\web\html\IconFactory::getInstance()->createText($icon, $text), self::variant($variant));
        return $b->setName(self::$SUBMIT_NAME)->setValue($value);
    }

	/**
	 * 
	 * @param CssVariant $v
	 * @return CssVariant
	 */
	private static function variant(CssVariant $v= null) {
		return _dif($v, CssVariant::$PRIMARY) ;
	}
	
    /**
     * 
     * @param mixed $innerText
     * @param \temple\web\html\bootstrap\CssVariant $variant
     * @return Button
     */
    public static function createReset($innerText, CssVariant $variant = null) {
        $b = new Button(self::TYPE_RESET, $innerText, $variant);
        return $b->setName('reset');
    }

    /**
     * 
     * @param type $modalId
     * @param mixed $innerText
     * @param \temple\web\html\bootstrap\CssVariant $variant
     * @return Button
     */
    public static function createModal($modalId, $innerText = null, CssVariant $variant = null) {
        return self::create($innerText, $variant)->setData(['toggle' => 'modal', 'target' => '#' . $modalId]);
    }

	/**
	 * 
	 * @param type $icon
	 * @param type $text
	 * @param \temple\web\html\Node $collapsable
	 * @param \temple\web\html\bootstrap\CssVariant $variant
	 * @param boolean $collapsed whether the $collapsable is initially collapsed or not
	 * @return Button
	 */
	public static function createCollapse($icon, $text, \temple\web\html\Node $collapsable, CssVariant $variant = null, $collapsed = true) {
		return self::create(\temple\web\html\IconFactory::getInstance()->createText($icon, $text), $variant)
				->setData([
					'target' => '#' . $collapsable->getId(),
					'toggle' => 'collapse' ]) 
				->setAria([
					'expanded' => $collapsed ? 'false' : 'true' ,
					'controls' => $collapsable->getId()
				]);
	}
	
}

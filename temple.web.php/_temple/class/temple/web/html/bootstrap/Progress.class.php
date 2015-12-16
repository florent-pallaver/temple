<?php

namespace temple\web\html\bootstrap;

/**
 * Description of Progress
 *
 * @author florent
 */
class Progress extends AbstractComponent {

	/**
	 * @var CssVariant
	 */
	private static $defaultVariant ;
	
	/**
	 * 
	 * @param int $progress
	 * @param boolean $visibleLabel
	 * @param CssVariant $cv
	 * @param string $cssClass
	 */
	public function __construct($progress, $visibleLabel = true, CssVariant $cv = null, $cssClass = null) {
		parent::__construct('div', $cssClass);
		$variant = _dif($cv, self::$defaultVariant) ;
		$this->addCssClass('progress')->addChild(
			$this->createHTMLNode('div', $variant->compose('progress-bar'), $this->createHTMLNode('span', $visibleLabel ? null : 'sr-only', $progress . '%'))
					->setRole('progressbar')
					->setAria(['valuenow' => $progress, 'valuemin' => 0, 'valuemax' => 100])
					->setAttribute('style', 'width: ' . $progress . '%;')
		) ;
	}
	
	/**
	 * @param CssVariant $cv
	 */
	public static function setDefaultVariant(CssVariant $cv) {
		self::$defaultVariant = $cv ;
	}

	private static function _init() {
		self::$defaultVariant = CssVariant::$_ ;
	}
	
}

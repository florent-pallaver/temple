<?php

namespace temple\web\html\fontawesome;

/**
 * Description of StackedIcons
 *
 * @author flominou
 */
class StackedIcons extends AbstractFontawesome {

	/**
	 * Constructor 
	 * 
	 * @param \temple\web\html\fontawesome\Icon $back
	 * @param \temple\web\html\fontawesome\Icon $front
	 * @param boolean $inverseFront 
	 */
	public function __construct(Icon $back, Icon $front, $inverseFront = false) {
		parent::__construct();
		if($inverseFront) {
			$front->addCssClass('fa-inverse') ;
		}
		$this->addChild($back->addCssClass('fa-stack-2x'))
				->addChild($front->addCssClass('fa-stack-1x')) 
				->addCssClass('fa-stack')
				;
	}
	
	/**
	 * 
	 * @param string $back
	 * @param string $front
	 * @param boolean $inverseFront
	 * @return \temple\web\html\fontawesome\StackedIcons
	 */
	public static function create($back, $front, $inverseFront = false) {
		return new StackedIcons(new Icon($back), new Icon($front), $inverseFront) ;
	}
	
}

<?php

namespace temple\web\html\fontawesome;

/**
 * Base class to render Fontawesome icons
 *
 * @author flominou
 */
class Icon extends AbstractFontawesome {
	
	/**
	 * Constructor
	 * 
	 * @param string $icon
	 * @param array $variations array of IconVariation
	 * @see IconVariation
	 */
	public function __construct($icon, array $variations = []) {
		parent::__construct();
		$this->addCssClass('fa fa-'.$icon) ;
		foreach ($variations as $v) {
			$this->addCssClass($v->toCssClass()) ;
		}
	}

}

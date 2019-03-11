<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace temple\web\html\fontawesome;

/**
 * Description of AbstractFontawesome
 *
 * @author flominou
 */
abstract class AbstractFontawesome extends \temple\web\html\AbstractFrameworkComponent {
	
	public function __construct() {
		parent::__construct('span');
	}

	/**
	 * Makes this icon larger
	 * @param IconSize $size
	 * @return \temple\web\html\fontawesome\AbstractFontawesome
	 */
	public final function larger(IconSize $size = null) {
		return $this->addCssClass(_dif($size, IconSize::$LG)) ;
	}
	

}

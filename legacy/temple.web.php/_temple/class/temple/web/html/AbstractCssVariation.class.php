<?php

namespace temple\web\html;

/**
 * Description of AbstractCssVariation
 *
 * @author flominou
 */
abstract class AbstractCssVariation extends \temple\Enumeration implements CssVariation {

	public final function __toString() {
		return $this->toCssClass() ;
	}
	
}

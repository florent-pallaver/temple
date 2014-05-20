<?php

namespace temple\web\html\bootstrap;

/**
 * Description of AbstractGroup
 *
 * @author florent
 */
abstract class AbstractGroup extends AbstractComponent {

	public function __construct($groupType, CssVariant $variant = null, $cssClass = null) {
		parent::__construct('div', $cssClass);
		$this->addCompositeCssClass($groupType . '-group', $variant) ;
	}
	
}

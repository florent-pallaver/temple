<?php

namespace temple\web\html\bootstrap;

/**
 * Description of Icon
 *
 * @author florent
 */
final class Icon extends AbstractComponent {

	/**
	 * 
	 * @param type $icon
	 * @param type $title
	 */
	public function __construct($icon, $title = null) {
		parent::__construct('span');
		$this->addCssClass('glyphicon')->addCssClass('glyphicon-' . $icon)->setAttribute('title', $title) ;
	}
	
}

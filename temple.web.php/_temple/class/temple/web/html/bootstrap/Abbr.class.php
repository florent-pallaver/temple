<?php

namespace temple\web\html\bootstrap;

/**
 * Description of Abbr
 *
 * @author florent
 */
class Abbr extends AbstractComponent {

	public function __construct($cssClass = null) {
		parent::__construct('abbr', $cssClass);
	}
	
	/**
	 * 
	 * @param type $abbr
	 * @param type $title
	 * @return Abbr
	 */
	public static function create($abbr, $title, $cssClass = null) {
		$a = new Abbr($cssClass) ;
		return $a->setTitle($title)->addChild(ComponentFactory::toHTMLElement($abbr)) ;
	}

}

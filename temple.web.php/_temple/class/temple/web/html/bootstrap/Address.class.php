<?php

namespace temple\web\html\bootstrap;

use temple\web\html\HTMLElementList ;
use temple\web\html\HTMLString ;
use temple\web\html\bootstrap\ComponentFactory as CF ;

/**
 * Description of Address
 *
 * @author florent
 */
class Address extends AbstractComponent {

	public function __construct($cssClass = null) {
		parent::__construct('address', $cssClass);
	}
	
	/**
	 * 
	 * @param type $line
	 * @return \temple\web\html\bootstrap\Address
	 */
	public function addLine($line, $withBr = true) {
		if($line) {
			$this->addChild(CF::toHTMLElement($line)) ;
			if($withBr) {
				$this->addChild(CF::$BR) ;
			}
		}
		return $this ;
	}

	/**
	 * 
	 * @param \temple\web\html\bootstrap\Abbr $abbr
	 * @param type $value
	 * @return Address
	 */
	public function addDefinition(Abbr $abbr, $value, $withBr = true) {
		return $this->addLine([$abbr, ': ', $value], $withBr) ;
	}

	/**
	 * 
	 * @param type $name
	 * @param type $line1
	 * @param type $line2
	 * @param type $withBr
	 * @return Address
	 */
	public static function create($name, $line1 = null , $line2 = null, $withBr = true) {
		$a = new Address() ;
		return $a->addLine(CF::createComponent('strong', null, $name))
				->addLine($line1)
				->addLine($line2, $withBr) ;
	}
	
}

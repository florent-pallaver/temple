<?php

namespace temple\web\html\bootstrap;

/**
 * Description of DefinitionList
 *
 * @author florent
 */
class DefinitionList extends AbstractComponent {

	/**
	 * 
	 * @param type $cssClass
	 */
	public function __construct($cssClass = null) {
		parent::__construct('dl', $cssClass);
	}

	/**
	 * 
	 * @param type $dt
	 * @param type $dd
	 * @param type $dtCssClass
	 * @param type $ddCssClass
	 * @return DefinitionList
	 */
	public final function addDefinition($dt, $dd, $dtCssClass = null, $ddCssClass = null) {
		return $this->addChild($this->createHTMLNode('dt', $dtCssClass, $dt))
			->addChild($this->createHTMLNode('dd', $ddCssClass, $dd)) ;
	}

	/**
	 * 
	 * @param type $cssClass
	 * @return DefinitionList
	 */
	public static function create($cssClass = null) {
		return new DefinitionList($cssClass);
	}

	/**
	 * 
	 * @param type $cssClass
	 * @return DefinitionList
	 */
	public static function createHorizontal($cssClass = null) {
		return self::create('dl-horizontal')->addCssClass($cssClass) ;
	}
	
}

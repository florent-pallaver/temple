<?php

namespace temple\web\html\bootstrap;

/**
 * Description of AbstractComponent
 *
 * @author florent
 */
abstract class AbstractComponent extends \temple\web\html\HTMLNode implements Component{

	public function __construct($nodeType, $cssClass = null) {
		parent::__construct($nodeType) ;
		$this->addCssClass($cssClass) ;
	}
	
	/**
	 * 
	 * @param type $cssClass
	 * @param array $complements
	 * @return AbstractComponent
	 */
	protected final function addCompositeCssClass($cssBaseClass, CssVariant $variant = null) {
		$variant_ = _dif($variant, CssVariant::$_) ;
		$this->addCssClass($variant_->compose($cssBaseClass)) ;
		return $this ;
	}
	
	/**
	 * 
	 * @param string $title
	 * @return AbstractComponent
	 */
	public final function setTitle($title) {
		return $this->setAttribute('title', $title) ;
	}
	
	/**
	 * 
	 * @param string $name
	 * @return AbstractComponent
	 */
	public final function setName($name) {
		return $this->setAttribute('name', $name) ;
	}
	
}

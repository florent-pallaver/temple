<?php

namespace temple\web\html\bootstrap;

/**
 * Description of AbstractComponent
 *
 * @author florent
 */
abstract class AbstractComponent extends \temple\web\html\HTMLNode implements Component {

	/**
	 * @var \temple\web\html\HTMLNode
	 */
	private $help ;

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
	public final function addCompositeCssClass($cssBaseClass, CssVariant $variant = null) {
		if($cssBaseClass) {
			$variant_ = _dif($variant, CssVariant::$_) ;
			$this->addCssClass($variant_->compose($cssBaseClass)) ;
		}
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
	
	/**
	 * 
	 * @param mixed $help 
	 * @return \temple\web\html\bootstrap\AbstractComponent
	 */
	public function setHelp($help) {
		$this->help = $help ? ComponentFactory::createComponent('small', 'help-block', $help) : null ;
		return $this ;
	}
	
	public function render() {
		parent::render();
		if(isset($this->help)) {
			$this->help->render() ;
		}
	}
	
	/**
	 * @return \temple\web\html\HTMLElement
	 */
	protected final function toHTMLElement($x) {
		return ComponentFactory::toHTMLElement($x) ;
	}
	
	/**
	 * 
	 * @param type $nodeName
	 * @param type $cssClass
	 * @param type $content
	 * @return \temple\web\html\HTMLNode
	 */
	protected final function createHTMLNode($nodeName, $cssClass = null, $content = null) {
		return ComponentFactory::createComponent($nodeName, $cssClass, $content) ;
	}
	
}

<?php

namespace temple\web\html\bootstrap;

/**
 * Description of AbstractComponent
 *
 * @author florent
 */
abstract class AbstractComponent extends \temple\web\html\AbstractFrameworkComponent implements Component {

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
	
	public final function getTitle() {
		return $this->getAttribute('title') ;
	}
	
	public final function setTitle($title) {
		return $this->setAttribute('title', $title) ;
	}
	
	public function getName() {
		return $this->getAttribute('name') ;
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
	 * 
	 * @param type $nodeName
	 * @param type $cssClass
	 * @param type $content
	 * @return \temple\web\html\HTMLNode
	 */
	protected final function createHTMLNode($nodeName, $cssClass = null, $content = null) {
		return ComponentFactory::createComponent($nodeName, $cssClass, $content) ;
	}
	
	/**
	 * 
	 * @return AbstractComponent
	 */
	public final function pullLeft() {
		return $this->addCssClass('pull-left') ;
	}
	
	/**
	 * 
	 * @return AbstractComponent
	 */
	public final function pullRight() {
		return $this->addCssClass('pull-right') ;
	}
	
}
